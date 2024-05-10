import groovy.json.JsonSlurper
import org.sonatype.nexus.security.realm.RealmManager

parsed_args = new JsonSlurper().parseText(args)

def realmManager = container.lookup(RealmManager.class.getName());
realmManager.enableRealm(parsed_args.name, parsed_args.enabled && parsed_args.enabled.toBoolean())
