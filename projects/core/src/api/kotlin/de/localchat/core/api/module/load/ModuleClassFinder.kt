package de.localchat.core.api.module.load

interface ModuleClassFinder {
    fun findModuleClasses(classLoader: ClassLoader): List<String>
}
