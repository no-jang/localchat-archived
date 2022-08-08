package de.localchat.network.environment

interface Environment {
    interface Factory<E : Environment> {
        fun newEnvironment(): E
    }

    fun getName(): String
}