import groovy.json.JsonSlurper
import org.sonatype.nexus.repository.config.Configuration
import org.sonatype.nexus.repository.manager.RepositoryManager

repositoryManager = container.lookup(RepositoryManager.class.getName())
parsed_args = new JsonSlurper().parseText(args)

Configuration configuration = repositoryManager.newConfiguration()
configuration.with{
        repositoryName = parsed_args.name
        recipeName = 'docker-hosted'
        online = true
        attributes = [
                docker: [
                        httpPort: parsed_args.http_port,
                        v1Enabled : parsed_args.v1_enabled
                ],
                storage: [
                        writePolicy: parsed_args.write_policy.toUpperCase(),
                        blobStoreName: parsed_args.blob_store,
                        strictContentTypeValidation: Boolean.valueOf(parsed_args.strict_content_validation),
                        latestPolicy: Boolean.valueOf(parsed_args.latest_policy)
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
