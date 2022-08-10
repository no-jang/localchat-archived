package de.localchat.common.lifecycle

import kotlinx.coroutines.flow.Flow

interface Closeable : AutoCloseable {
    fun onClose(): Flow<Unit>
}