import groovy.json.JsonSlurper
import org.sonatype.nexus.repository.config.Configuration
import org.sonatype.nexus.repository.manager.RepositoryManager

repositoryManager = container.lookup(RepositoryManager.class.getName())
parsed_args = new JsonSlurper().parseText(args)

Configuration configuration = repositoryManager.newConfiguration()
configuration.with{
        repositoryName = parsed_args.name
        recipeName = 'composer-proxy'
        online = true
        attributes = [
                composer: [
                        httpPort: parsed_args.http_port,
                        v1Enabled : parsed_args.v1_enabled
                ],
                proxy: [
                        remoteUrl: parsed_args.remote_url,
                        contentMaxAge: 1440,
                        metadataMaxAge: 1440
                ],
                composerProxy: [
                        indexType: parsed_args.index_type,
                        useTrustStoreForIndexAccess: parsed_args.use_nexus_certificates_to_access_index
                ],
                httpclient: [
                        blocked: false,
                        autoBlock: true,
                        connection: [
                          useTrustStore: false
                        ]
                ],
                storage: [
                        writePolicy: parsed_args.write_policy.toUpperCase(),
                        blobStoreName: parsed_args.blob_store,
                        strictContentTypeValidation: Boolean.valueOf(parsed_args.strict_content_validation)
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
