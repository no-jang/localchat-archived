package de.localchat.core.module.load

import de.localchat.core.api.module.Module
import de.localchat.core.api.module.load.ModuleClassFinder
import org.tinylog.kotlin.Logger

class ServiceModuleClassFinder : ModuleClassFinder {
    override fun findModuleClasses(classLoader: ClassLoader): List<String> {
        val serviceResource = classLoader.getResourceAsStream("SERVICE_RESOURCE")

        if(serviceResource == null) {
            Logger.debug("ServiceModuleClassFinder didn't find {} resource in classpath {}", SERVICE_RESOURCE)
            return emptyList()
        }

        TODO()
    }

    companion object {
        const val SERVICE_RESOURCE = "META-INF/services/de.localchat.core.api.module.Module"
    }
}
