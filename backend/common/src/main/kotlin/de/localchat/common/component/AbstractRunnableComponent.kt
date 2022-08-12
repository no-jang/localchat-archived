package de.localchat.common.component

import de.localchat.common.lifecycle.LifecycleState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AbstractRunnableComponent : AbstractComponent(), RunnableComponent {
    private val beforeStartFlow = MutableSharedFlow<Unit>()
    private val afterStartFlow = MutableSharedFlow<Unit>()
    private val beforeStopFlow = MutableSharedFlow<Unit>()
    private val afterStopFlow = MutableSharedFlow<Unit>()

    override fun onBeforeStart(): Flow<Unit> = beforeStartFlow
    override fun onAfterStart(): Flow<Unit> = afterStartFlow
    override fun onBeforeStop(): Flow<Unit> = beforeStopFlow
    override fun onAfterStop(): Flow<Unit> = afterStopFlow

    abstract fun doStart()
    abstract fun doStop()

    override fun start() {
        if (canBeStarted) {
            beforeStartFlow.tryEmit(Unit)
            doStart()
            state = LifecycleState.STARTED
            afterStartFlow.tryEmit(Unit)
        }
    }

    override fun stop() {
        if (canBeStopped) {
            beforeStopFlow.tryEmit(Unit)
            doStop()
            state = LifecycleState.STOPPED
            afterStopFlow.tryEmit(Unit)
        }
    }
}