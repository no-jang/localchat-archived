import de.localchat.gradle.message.MessagePlugin
import de.localchat.gradle.node.common.Constants
import de.localchat.gradle.node.services.NodeService

if(project != rootProject) {
    error("This plugin can only be applied to the root project")
}

val message = plugins.getPlugin(MessagePlugin::class)

val service = gradle.sharedServices.registerIfAbsent(Constants.NODE_SERVICE, NodeService::class) {
    maxParallelUsages.set(1)
}

message.addResource(NodeService::class, service)

/*
val task = tasks.register(Constants.NODE_TASK, NodeTask::class) {
    getService().set(service)
    usesService(service)
}
*/
