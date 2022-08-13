package de.localchat.common.environment

enum class OperatingSystem {
    WINDOWS,
    LINUX,
    MACOS,
    UNKNOWN;

    companion object {
        val currentOS: OperatingSystem

        init {
            val property = System.getProperty("os.name").lowercase()
            currentOS = when {
                property.contains("win") -> WINDOWS
                property.contains("nux") -> LINUX
                property.contains("mac") -> MACOS
                else -> UNKNOWN
            }
        }
    }
}