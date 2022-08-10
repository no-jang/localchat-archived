package de.localchat.common.lifecycle

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class AbstractCloseable : Closeable {
    private val closeChannel = Channel<Unit>()

    abstract fun doClose()

    override fun onClose(): Flow<Unit> = closeChannel.receiveAsFlow()

    override fun close() {
        doClose()
        fireOnClose()
    }

    protected fun fireOnClose() {
        closeChannel.trySend(Unit)
    }
}