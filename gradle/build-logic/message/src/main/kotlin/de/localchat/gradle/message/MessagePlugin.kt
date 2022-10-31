package de.localchat.gradle.message

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import kotlin.reflect.KClass

class MessagePlugin : Plugin<Project> {
    private val resources: MutableMap<KClass<*>, Provider<*>> = mutableMapOf()

    override fun apply(target: Project) {
        if(target != target.rootProject) {
            error("This plugin can only be applied to the root project")
        }
    }

    fun <T> addResource(clazz: KClass<T>, resource: Provider<T>) where T : Any {
        if(resources.containsKey(clazz)) {
            error("Resource $clazz already exists")
        }
        resources[clazz] = resource
    }

    fun <T> getResource(resource: KClass<T>): Provider<T> where T : Any {
        return resources[resource] as Provider<T>
    }
}
