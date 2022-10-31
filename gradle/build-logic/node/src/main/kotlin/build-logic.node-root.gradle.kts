import de.localchat.gradle.message.MessagePlugin
import de.localchat.gradle.node.common.Constants
import de.localchat.gradle.common.services.ExecutionService

if(project != rootProject) {
    error("This plugin can only be applied to the root project")
}

val message = plugins.getPlugin(MessagePlugin::class)

val service = gradle.sharedServices.registerIfAbsent(Constants.NODE_SERVICE, ExecutionService::class) {
    maxParallelUsages.set(1)
    parameters {
        workingDir.set(layout.projectDirectory)
        executable.set("pnpm")
        cmd.set("turbo")
    }
}

message.addResource(ExecutionService::class, service)
