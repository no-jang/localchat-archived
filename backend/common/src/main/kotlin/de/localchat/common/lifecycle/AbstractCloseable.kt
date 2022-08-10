package de.localchat.common.lifecycle

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class AbstractCloseable : Closeable {
    private val closeChannel = Channel<Unit>()

    override fun onClose(): Flow<Unit> = closeChannel.receiveAsFlow()

    protected fun callOnClose() {
        closeChannel.trySend(Unit)
    }
}