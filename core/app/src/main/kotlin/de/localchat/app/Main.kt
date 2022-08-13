package de.localchat.app

import de.localchat.plugin.TestExtensionPoint
import org.pf4j.DefaultPluginManager

fun main() {
    val pluginManager = DefaultPluginManager()
    pluginManager.loadPlugins()
    pluginManager.startPlugins()

    val extensions = pluginManager.getExtensions(TestExtensionPoint::class.java)
    extensions.forEach {
        it.print()
    }
}