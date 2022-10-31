package de.localchat.gradle.node.services

import org.gradle.api.Project
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.tasks.Exec
 abstract class NodeService : BuildService<NodeService.Params> {
    abstract class Params : BuildServiceParameters {

    }

    fun build(s: String)  {
        println("Build!!! $s")
    }
}
