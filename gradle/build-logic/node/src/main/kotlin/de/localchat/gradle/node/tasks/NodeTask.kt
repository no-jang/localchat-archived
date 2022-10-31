package de.localchat.gradle.node.tasks

import de.localchat.gradle.node.common.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import de.localchat.gradle.node.services.NodeService

abstract class NodeTask : DefaultTask() {
    @Internal
    abstract fun getService(): Property<NodeService>

    init {
        group = Constants.NODE_GROUP
    }

    @TaskAction
    fun execute() {
        getService().get().build("Hello World")
    }
}
