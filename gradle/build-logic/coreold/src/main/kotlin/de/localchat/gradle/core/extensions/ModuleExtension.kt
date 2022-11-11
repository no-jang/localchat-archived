package de.localchat.gradle.core.extensions

import org.gradle.api.Project
import org.gradle.api.provider.Property

abstract class ModuleExtension(project: Project) {
    abstract val name: Property<String>

    init {
        name.convention(project.name)
    }
}
