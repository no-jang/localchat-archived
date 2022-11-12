package de.localchat.gradle.node

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Provider

class NodeRootPlugin : Plugin<Project> {
    lateinit var service: Provider<ExecutionService>

    override fun apply(target: Project) {
        if (target != target.rootProject) {
            error("This plugin can only be applied to the root project")
        }

        service = target.gradle.sharedServices.registerIfAbsent(NODE_ROOT_SERVICE, ExecutionService::class.java) {
            maxParallelUsages.set(1)
            parameters {
                workingDir.set(target.layout.projectDirectory)
            }
        }
    }

    companion object {
        const val NODE_ROOT_SERVICE = "node"
    }
}

fun Project.getNodeRoot(): NodeRootPlugin {
    return rootProject.plugins.findPlugin(NodeRootPlugin::class.java)
        ?: error("The NodeRootPlugin has not been applied to the root project")
}
