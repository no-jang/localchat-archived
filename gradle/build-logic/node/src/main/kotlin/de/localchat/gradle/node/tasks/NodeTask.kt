package de.localchat.gradle.node.tasks

import de.localchat.gradle.node.common.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import de.localchat.gradle.common.services.ExecutionService
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.Input
import java.io.File

abstract class NodeTask : DefaultTask() {
    @Internal
    abstract fun getService(): Property<ExecutionService>

    @get:Input
    abstract val args: ListProperty<String>

    init {
        group = Constants.NODE_GROUP
    }

    @TaskAction
    fun execute() {
        getService().get().execute(args.get())
    }
}
