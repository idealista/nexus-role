import groovy.json.JsonSlurper
import org.sonatype.nexus.repository.routing.RoutingRuleStore
import org.sonatype.nexus.repository.routing.RoutingMode

RoutingRuleStore routingRuleStore = container.lookup(RoutingRuleStore.class.getName())
parsed_args = new JsonSlurper().parseText(args)

def existingRule = routingRuleStore.getByName(parsed_args.name)

if (existingRule != null) {
    existingRule.description(parsed_args.description ?: '')
    existingRule.mode(RoutingMode.valueOf(parsed_args.mode.toUpperCase()))
    existingRule.matchers(parsed_args.matchers as List)
    routingRuleStore.update(existingRule)
} else {
    def rule = routingRuleStore.newRoutingRule()
            .name(parsed_args.name)
            .description(parsed_args.description ?: '')
            .mode(RoutingMode.valueOf(parsed_args.mode.toUpperCase()))
            .matchers(parsed_args.matchers as List)
    routingRuleStore.create(rule)
}
