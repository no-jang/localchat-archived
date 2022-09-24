package de.localchat.core.api.module

import com.intuit.hooks.Hook
import com.intuit.hooks.dsl.HooksDsl

interface Module {
    abstract class Hooks : HooksDsl() {
        @Sync<(name: String) -> Unit>
        abstract val start: Hook

        @Sync<() -> Unit>
        abstract val stop: Hook
    }

    val hooks: Hooks
}