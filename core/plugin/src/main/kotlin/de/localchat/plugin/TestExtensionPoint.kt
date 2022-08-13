package de.localchat.plugin

import org.pf4j.ExtensionPoint

interface TestExtensionPoint : ExtensionPoint {
    fun print()
}