package de.localchat.gradle.node

import de.localchat.gradle.node.root.getNodeRoot
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.configurationcache.extensions.capitalized

class NodePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val root = target.getNodeRoot()

        root.extension.tasks.get().forEach { task ->
            target.tasks.create("turbo${task.capitalized()}", TurboTask::class.java) {
                group = "node"

                tasks.add(task)
                workspaceRoot.set(target.rootProject.layout.projectDirectory)
                workspaceDirectory.set(target.layout.projectDirectory)

                usesService(root.service)
                getService().set(root.service)
            }
        }
    }

    companion object {
        const val NODE_GROUP = "node"
    }
}
