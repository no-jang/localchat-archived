package de.localchat.gradle.node.tasks

abstract class NpmTask : NodeTask() {
    init {
        cmd.convention("pnpm")
    }
}
