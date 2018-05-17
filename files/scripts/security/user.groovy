import groovy.json.JsonSlurper
import org.sonatype.nexus.security.user.UserNotFoundException

parsed_args = new JsonSlurper().parseText(args)

try {
    user = security.securitySystem.getUser(parsed_args.username)
    updateUser(user, parsed_args)
} catch (UserNotFoundException ignored) {
    createUser(parsed_args)
}

def updateUser(user, args) {
    user.setFirstName(args.first_name)
    user.setLastName(args.last_name)
    user.setEmailAddress(args.email)
    security.securitySystem.updateUser(user)
    security.setUserRoles(args.username, args.roles)
    security.securitySystem.changePassword(args.username, args.password)
}

def createUser(args) {
    security.addUser(args.username, args.first_name, args.last_name, args.email, true, args.password, args.roles)
}
