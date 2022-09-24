plugins {
    id("org.jetbrains.dokka")
}

// Disable configuration cache for every dokka task
tasks.filter { it.name.contains("dokka")}.forEach {
    it.notCompatibleWithConfigurationCache("Dokka is not compatible with configuration cache")
}
