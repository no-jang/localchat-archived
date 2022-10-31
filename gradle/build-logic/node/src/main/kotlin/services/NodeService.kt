package services

import org.gradle.api.Project
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.api.tasks.Exec

class NodeService {
    fun build(project: Project)  {
        println("Build!!! ${project.name}")
    }
}
