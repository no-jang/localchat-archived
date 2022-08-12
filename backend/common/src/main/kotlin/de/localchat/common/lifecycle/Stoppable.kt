package de.localchat.common.lifecycle

import kotlinx.coroutines.flow.Flow

interface Stoppable : Lifecycle {
    val stopped: Boolean
        get() = state == LifecycleState.INITIALIZED || state == LifecycleState.STARTED

    val canBeStopped: Boolean
        get() = state == LifecycleState.STARTED

    fun onBeforeStop(): Flow<Unit>
    fun onAfterStop(): Flow<Unit>

    fun stop()
}