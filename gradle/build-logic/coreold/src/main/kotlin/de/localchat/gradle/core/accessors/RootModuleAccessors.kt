package de.localchat.gradle.core.accessors

import de.localchat.gradle.core.extensions.ModuleRootExtension
import de.localchat.gradle.core.plugins.ModuleRootPlugin
import org.gradle.api.Project

fun Project.getRootModule(): Triple<Project, ModuleRootPlugin, ModuleRootExtension> {
    val module = rootProject.plugins.findPlugin(ModuleRootPlugin::class.java)
        ?: error("Root project does not have the root module plugin applied")

    val extension = rootProject.extensions.getByType(ModuleRootExtension::class.java)

    return Triple(rootProject, module, extension)
}
