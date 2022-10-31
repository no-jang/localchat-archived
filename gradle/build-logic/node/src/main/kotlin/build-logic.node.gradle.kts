import de.localchat.gradle.message.MessagePlugin
import de.localchat.gradle.node.common.Constants
import de.localchat.gradle.node.services.NodeService
import de.localchat.gradle.node.tasks.NodeTask

val message = rootProject.plugins.getPlugin(MessagePlugin::class)
val service = message.getResource(NodeService::class)

val task = tasks.register(Constants.NODE_TASK, NodeTask::class) {
    getService().set(service)
    usesService(service)
}
