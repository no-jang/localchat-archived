package de.localchat.core.api.module

interface Module {
    val name: String

    fun onEnable()
    fun onDisable()
}