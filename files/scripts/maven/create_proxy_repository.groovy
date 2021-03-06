import groovy.json.JsonSlurper
import org.sonatype.nexus.repository.config.Configuration
import org.sonatype.nexus.repository.manager.RepositoryManager

RepositoryManager repositoryManager = container.lookup(RepositoryManager.class.getName())
parsed_args = new JsonSlurper().parseText(args)

authentication = parsed_args.remote_username == null ? null : [
        type: 'username',
        username: parsed_args.remote_username,
        password: parsed_args.remote_password
]

Configuration configuration = repositoryManager.newConfiguration()
configuration.with {
        repositoryName = parsed_args.name
        recipeName = 'maven2-proxy'
        online = true
        attributes = [
                maven  : [
                        versionPolicy: parsed_args.version_policy.toUpperCase(),
                        layoutPolicy : parsed_args.layout_policy.toUpperCase()
                ],
                proxy  : [
                        remoteUrl: parsed_args.remote_url,
                        contentMaxAge: 1440.0,
                        metadataMaxAge: 1440.0
                ],
                httpclient: [
                        blocked: false,
                        autoBlock: true,
                        authentication: authentication,
                        connection: [
                                useTrustStore: false,
                                enableCircularRedirects: Boolean.valueOf(parsed_args.enableCircularRedirects),
                                enableCookies: Boolean.valueOf(parsed_args.enableCircularRedirects),
                                timeout: parsed_args.timeout,
                                retries: parsed_args.retries
                        ]
                ],
                storage: [
                        blobStoreName: parsed_args.blob_store,
                        strictContentTypeValidation: Boolean.valueOf(parsed_args.strict_content_validation)
                ],
                negativeCache: [
                        enabled: true,
                        timeToLive: 1440.0
                ],
                cleanup: [
                        policyName: new HashSet<String>([parsed_args.clean_policy]) 
                ]
        ]
}

def existingRepository = repositoryManager.get(parsed_args.name)

if (existingRepository != null) {
    existingRepository.stop()
    configuration.attributes['storage']['blobStoreName'] = existingRepository.configuration.attributes['storage']['blobStoreName']
    existingRepository.update(configuration)
    existingRepository.start()
} else {
    repositoryManager.create(configuration)
}
