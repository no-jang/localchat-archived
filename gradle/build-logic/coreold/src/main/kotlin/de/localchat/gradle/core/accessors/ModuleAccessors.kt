package de.localchat.gradle.core.accessors

import de.localchat.gradle.core.extensions.ModuleExtension
import de.localchat.gradle.core.plugins.ModulePlugin
import org.gradle.api.Project

fun Project.getModule(): Triple<Project, ModulePlugin, ModuleExtension> {
    if (project == rootProject) {
        error("Cannot get module from root project")
    }

    var iteration: Project? = project
    while (iteration != null) {
        val module = iteration.plugins.findPlugin(ModulePlugin::class.java)

        if (module != null) {
            val extension = iteration.extensions.getByType(ModuleExtension::class.java)
            return Triple(iteration, module, extension)
        }

        iteration = iteration.parent
    }

    error("Cannot find module for project ${project.path}")
}
