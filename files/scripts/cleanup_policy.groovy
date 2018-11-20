import groovy.json.JsonSlurper
import java.util.concurrent.TimeUnit

import org.sonatype.nexus.cleanup.storage.CleanupPolicy
import org.sonatype.nexus.cleanup.storage.CleanupPolicyStorage
import com.google.common.collect.Maps;

import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.IS_PRERELEASE_KEY
import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.LAST_BLOB_UPDATED_KEY
import static org.sonatype.nexus.repository.search.DefaultComponentMetadataProducer.LAST_DOWNLOADED_KEY


def cleanupPolicyStorage = container.lookup(CleanupPolicyStorage.class.getName())

parsed_args = new JsonSlurper().parseText(args)
Map<String, String> criteriaMap = createCriteria(parsed_args)

if (cleanupPolicyStorage.exists(parsed_args.name)) {
    existingPolicy = cleanupPolicyStorage.get(parsed_args.name)
    existingPolicy.setNotes(parsed_args.notes)
    existingPolicy.setCriteria(criteriaMap)
    cleanupPolicyStorage.update(existingPolicy)
} else {
    format = parsed_args.format == "all" ? "ALL_FORMATS" : parsed_args.format
    cleanupPolicy = new CleanupPolicy(
        name: parsed_args.name,
        notes: parsed_args.notes,
        format: format,
        mode: 'deletion',
        criteria: criteriaMap
    )
    cleanupPolicyStorage.add(cleanupPolicy)
}

def Map<String, String> createCriteria(args) {
    Map<String, String> criteriaMap = Maps.newHashMap()
    if (parsed_args.published_before.isInteger()) {
        criteriaMap.put(LAST_BLOB_UPDATED_KEY, asStringSeconds(parsed_args.published_before))
    }
    if (parsed_args.last_download_before.isInteger()) {
        criteriaMap.put(LAST_DOWNLOADED_KEY, asStringSeconds(parsed_args.last_download_before))
    }
    if (parsed_args.is_pre_release != "") {
        criteriaMap.put(IS_PRERELEASE_KEY, String.valueOf(parsed_args.is_pre_release))
    }
    return criteriaMap
}

def Integer asSeconds(days) {
    return days * TimeUnit.DAYS.toSeconds(1)
}

def String asStringSeconds(daysString) {
    return String.valueOf(asSeconds(Integer.parseInt(daysString)))
}
