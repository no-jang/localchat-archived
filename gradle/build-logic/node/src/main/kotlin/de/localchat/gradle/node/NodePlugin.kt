package de.localchat.gradle.node

import org.gradle.api.Plugin
import org.gradle.api.Project

class NodePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val root = target.getNodeRoot()

        target.afterEvaluate {
            target.tasks.withType(NodeTask::class.java).configureEach {
                usesService(root.service)
                getService().set(root.service)
            }
        }
    }

    companion object {
        const val NODE_GROUP = "node"
    }
}
