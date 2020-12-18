import org.sonatype.nexus.ldap.persist.LdapConfigurationManager
import org.sonatype.nexus.ldap.persist.entity.LdapConfiguration
import org.sonatype.nexus.ldap.persist.entity.Connection
import org.sonatype.nexus.ldap.persist.entity.Mapping
import groovy.json.JsonSlurper

parsed_args = new JsonSlurper().parseText(args)


def ldapConfigMgr = container.lookup(LdapConfigurationManager.class.getName());

def ldapConfig = ldapConfigMgr.newConfiguration()
boolean update = false;

ldapConfigMgr.listLdapServerConfigurations().each {
    if (it.name == parsed_args.name) {
        ldapConfig = it
        update = true
    }
}

ldapConfig.setName(parsed_args.name)

// Connection
connection = new Connection()
connection.setHost(new Connection.Host(Connection.Protocol.valueOf(parsed_args.protocol), parsed_args.host, Integer.valueOf(parsed_args.port)))
if (parsed_args.manager_dn != '' && parsed_args.password != '') {
    connection.setAuthScheme('simple')
    connection.setSystemUsername(parsed_args.manager_dn)
    connection.setSystemPassword(parsed_args.password)
} else {
    connection.setAuthScheme('none')
}
connection.setSearchBase(parsed_args.search_base)
connection.setConnectionTimeout(30)
connection.setConnectionRetryDelay(300)
connection.setMaxIncidentsCount(3)
connection.setUseTrustStore(Boolean.valueOf(parsed_args.use_trust_store))
ldapConfig.setConnection(connection)

// Mapping
mapping = new Mapping()
mapping.setUserBaseDn(parsed_args.base_dn)
mapping.setLdapFilter(parsed_args.filter)
mapping.setUserObjectClass(parsed_args.user_object_class)
mapping.setUserIdAttribute(parsed_args.user_id_attribute)
mapping.setUserRealNameAttribute(parsed_args.user_real_name_attribute)
mapping.setEmailAddressAttribute(parsed_args.user_email_attribute)
mapping.setUserSubtree(parsed_args.user_subtree)

if (parsed_args.map_groups_as_roles) {
    mapping.setLdapGroupsAsRoles(true)
    mapping.setGroupBaseDn(parsed_args.group_base_dn)
    mapping.setGroupObjectClass(parsed_args.group_object_class)
    mapping.setGroupIdAttribute(parsed_args.group_id_attribute)
    mapping.setGroupMemberAttribute(parsed_args.group_member_attribute)
    mapping.setGroupMemberFormat(parsed_args.group_member_format)
    mapping.setGroupSubtree(parsed_args.group_subtree)
}

ldapConfig.setMapping(mapping)

if (update) {
    ldapConfigMgr.updateLdapServerConfiguration(ldapConfig)
} else {
    ldapConfigMgr.addLdapServerConfiguration(ldapConfig)
}
