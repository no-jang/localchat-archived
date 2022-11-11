package de.localchat.gradle.node.plugins

import de.localchat.gradle.core.accessors.getModule
import de.localchat.gradle.core.accessors.getRootModule
import de.localchat.gradle.node.extensions.NodeExtension
import de.localchat.gradle.node.services.ExecutionService
import de.localchat.gradle.node.tasks.NodeTask
import de.localchat.gradle.node.tasks.TurboTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class NodePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val root = target.getRootModule()
        val module = target.getModule()

        val extension = target.extensions.create(NODE_EXTENSION, NodeExtension::class.java)

        val resources = root.second.resources
        val service = resources.getResource(ExecutionService::class)

        target.afterEvaluate {
            extension.pipelines.get().forEach {
                tasks.create("turbo${it.capitalize()}", TurboTask::class.java) {
                    args.add(it)
                }
            }

            target.tasks.withType(NodeTask::class.java) {
                getService().set(service)
                usesService(service)
            }

            target.tasks.withType(TurboTask::class.java) {
                modules.convention(provider { listOf(module.third.name.get()) })
            }
        }
    }

    companion object {
        const val NODE_EXTENSION = "node"
        const val NODE_GROUP = "node"
    }
}
