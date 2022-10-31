package de.localchat.gradle.node.tasks

import de.localchat.gradle.node.plugins.NodePlugin
import de.localchat.gradle.node.services.ExecutionService
import org.gradle.api.DefaultTask
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction

abstract class NodeTask : DefaultTask() {
    @Internal
    abstract fun getService(): Property<ExecutionService>

    @get:Input
    abstract val cmd: Property<String>

    @get:Input
    abstract val args: ListProperty<String>

    init {
        group = NodePlugin.NODE_GROUP
    }

    @TaskAction
    open fun execute() {
        getService().get().execute(cmd.get(), args.get())
    }
}
