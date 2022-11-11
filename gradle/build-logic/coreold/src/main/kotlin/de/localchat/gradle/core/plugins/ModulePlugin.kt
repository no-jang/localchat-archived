package de.localchat.gradle.core.plugins

import de.localchat.gradle.core.extensions.ModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.extensions.create(MODULE_EXTENSION, ModuleExtension::class.java)
    }

    companion object {
        const val MODULE_EXTENSION = "module"
    }
}
