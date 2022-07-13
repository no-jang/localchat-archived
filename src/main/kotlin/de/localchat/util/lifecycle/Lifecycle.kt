package de.localchat.util.lifecycle
interface Lifecycle : AutoCloseable {
    fun open()

    override fun close()
}