package de.localchat.common.component

import de.localchat.common.lifecycle.LifecycleState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AbstractComponent : Component {
    final override var state: LifecycleState = LifecycleState.STARTED
        protected set

    private val beforeDestroyFlow = MutableSharedFlow<Unit>()
    private val afterDestroyFlow = MutableSharedFlow<Unit>()

    override fun onBeforeDestroy(): Flow<Unit> = beforeDestroyFlow
    override fun onAfterDestroy(): Flow<Unit> = afterDestroyFlow

    abstract fun doDestroy()

    override fun destroy() {
        if (canBeDestroyed) {
            beforeDestroyFlow.tryEmit(Unit)
            doDestroy()
            state = LifecycleState.DESTROYED
            afterDestroyFlow.tryEmit(Unit)
        }
    }
}