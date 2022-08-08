package de.localchat.network.library.environment

interface Environment {
    interface Factory<E : Environment> {
        fun newEnvironment(): E
    }

    fun getName(): String
}