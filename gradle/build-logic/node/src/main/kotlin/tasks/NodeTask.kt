package tasks

import common.Constants
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import services.NodeService

abstract class NodeTask : DefaultTask() {
    init {
        group = Constants.NODE_GROUP
    }

    @TaskAction
    fun execute() {

    }
}
