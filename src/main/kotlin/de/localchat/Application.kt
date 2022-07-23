package de.localchat

//import de.localchat.discovery.backend.DiscoveryBackend
import de.localchat.web.WebService
import de.localchat.web.javlin.JavlinWebService
import org.koin.core.component.KoinComponent
import org.tinylog.kotlin.Logger

class Application : KoinComponent {
    //private val discoveryService: DiscoveryBackend = get()

    private val webservice: WebService = JavlinWebService()

    suspend fun start() {
        Logger.info("Start LocalChat")
    }

    suspend fun stop() {
        Logger.info("Stop LocalChat")
    }
}