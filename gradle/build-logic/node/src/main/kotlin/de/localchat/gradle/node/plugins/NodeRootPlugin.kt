package de.localchat.gradle.node.plugins

import de.localchat.gradle.core.accessors.getRootModule
import de.localchat.gradle.node.services.ExecutionService
import org.gradle.api.Plugin
import org.gradle.api.Project

class NodeRootPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        if (target != target.rootProject) {
            error("This plugin can only be applied to the root project")
        }

        val service = target.gradle.sharedServices.registerIfAbsent(NODE_ROOT_SERVICE, ExecutionService::class.java) {
            maxParallelUsages.set(1)
            parameters {
                workingDir.set(target.layout.projectDirectory)
            }
        }

        val resources = target.getRootModule().second.resources
        resources.addResource(ExecutionService::class, service)
    }

    companion object {
        const val NODE_ROOT_SERVICE = "node"
    }
}
