package de.localchat.gradle.turbo

import de.localchat.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

class TurboPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.apply(NodePlugin::class.java)

        target.afterEvaluate {
            target.tasks.withType(TurboTask::class.java).configureEach {
                workspaceRoot.convention(target.rootProject.layout.projectDirectory)
                workspaceDirectory.convention(target.layout.projectDirectory)
            }
        }
    }
}
