package de.localchat.core

import de.localchat.core.api.Core

class DefaultCore : Core {
    override fun print() {
        println("Hello World!")
    }

    override fun test(): String {
        return "Hello World!"
    }
}
