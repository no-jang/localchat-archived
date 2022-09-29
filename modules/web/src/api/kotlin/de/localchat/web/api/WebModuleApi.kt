package de.localchat.web.api

interface WebModuleApi {
    fun request(url: String): String
}
