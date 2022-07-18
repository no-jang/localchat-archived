package de.localchat

import de.localchat.discovery.DefaultClientDiscovery
import de.localchat.discovery.DiscoveryBackend
import de.localchat.web.WebService
import de.localchat.web.javlin.JavlinWebService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.annotation.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.tinylog.kotlin.Logger

@Single(binds = [Application::class], createdAtStart = true)
class DefaultApplication : Application, KoinComponent {
    private val discoveryService: DiscoveryBackend = get()

    private val webservice: WebService = JavlinWebService()

    init {
        Logger.info("Start LocalChat")

        runBlocking {
            launch {
                discoveryService.discoveryEvent().collect {
                    println("Discovered: $it")
                }
            }

            launch {
                while (true) {
                    discoveryService.send(DefaultClientDiscovery("marek", "Hello World", 1234))
                    delay(1500L)
                }
            }
        }
    }
}