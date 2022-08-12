package de.localchat.common.lifecycle

import kotlinx.coroutines.flow.Flow

interface Destroyable : Lifecycle {
    val destroyed: Boolean
        get() = state == LifecycleState.DESTROYED

    val canBeDestroyed: Boolean
        get() = !destroyed

    fun onBeforeDestroy(): Flow<Unit>
    fun onAfterDestroy(): Flow<Unit>

    fun destroy()
}