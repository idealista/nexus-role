import groovy.json.JsonSlurper
import org.sonatype.nexus.security.user.UserManager
import org.sonatype.nexus.security.role.NoSuchRoleException

parsed_args = new JsonSlurper().parseText(args)
parsed_args.privileges = (parsed_args.privileges == null ? new HashSet() : parsed_args.privileges.toSet())
parsed_args.roles = (parsed_args.roles == null ? new HashSet() : parsed_args.roles.toSet())

authManager = security.getSecuritySystem().getAuthorizationManager(UserManager.DEFAULT_SOURCE)

try {
    role = authManager.getRole(parsed_args.id)
    updateRole(role, parsed_args)
} catch (NoSuchRoleException ignored) {
    createRole(parsed_args)
}

def updateRole(role, args) {
    role.setName(args.name)
    role.setDescription(args.description)
    role.setPrivileges(args.privileges)
    role.setRoles(args.roles)
    authManager.updateRole(role)
}

def createRole(args) {
    security.addRole(args.id, args.name, args.description, args.privileges.toList(), args.roles.toList())
}
