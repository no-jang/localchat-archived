package de.localchat.common.lifecycle

import kotlinx.coroutines.flow.Flow

interface Startable : Lifecycle {
    val started: Boolean
        get() = state == LifecycleState.STARTED

    val canBeStarted: Boolean
        get() = state == LifecycleState.INITIALIZED || state == LifecycleState.STOPPED

    fun onBeforeStart(): Flow<Unit>
    fun onAfterStart(): Flow<Unit>

    fun start()
}