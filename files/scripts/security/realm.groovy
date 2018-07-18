import groovy.json.JsonSlurper
import org.sonatype.nexus.security.realm.RealmManager
import org.sonatype.nexus.security.realm.RealmConfiguration

parsed_args = new JsonSlurper().parseText(args)

def realmManager = container.lookup(RealmManager.class.getName());
RealmConfiguration realmConfig = realmManager.getConfiguration()
realmManager.enableRealm(parsed_args.name, parsed_args.enabled && parsed_args.enabled.toBoolean())
