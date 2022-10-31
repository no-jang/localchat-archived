package de.localchat.gradle.core.plugins

import de.localchat.gradle.core.extensions.ModuleRootExtension
import de.localchat.gradle.core.resource.ResourceManager
import org.gradle.api.Plugin
import org.gradle.api.Project

class ModuleRootPlugin : Plugin<Project> {
    val resources = ResourceManager()

    override fun apply(target: Project) {
        if (target != target.rootProject) {
            error("This plugin can only be applied to the root project")
        }

        target.extensions.create(EXTENSION_NAME, ModuleRootExtension::class.java)
    }

    companion object {
        const val EXTENSION_NAME = "rootModule"
    }
}
