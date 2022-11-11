package de.localchat.gradle.core.resource

import org.gradle.api.provider.Provider
import kotlin.reflect.KClass

class ResourceManager {
    private val resources = mutableMapOf<KClass<*>, Provider<*>>()

    fun <T> addResource(clazz: KClass<T>, resource: Provider<T>) where T : Any {
        if (resources.containsKey(clazz)) {
            error("Resource $clazz already exists")
        }
        resources[clazz] = resource
    }

    fun <T> getResource(resource: KClass<T>): Provider<T> where T : Any {
        return resources[resource] as Provider<T>
    }
}
