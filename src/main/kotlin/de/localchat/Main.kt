package de.localchat

import de.localchat.di.modules.mainModule
import de.localchat.discovery.DiscoveryBackend
import de.localchat.discovery.common.DefaultClientDiscovery
import de.localchat.util.lifecycle.Lifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin

fun main(args: Array<String>) {
    val koin = startKoin {
        printLogger()
        modules(mainModule)
    }

    val discoveryBackend: DiscoveryBackend = koin.koin.get()
    discoveryBackend.open()

    val lifecycles = koin.koin.getAll<Lifecycle>()

    runBlocking {
        launch {
            discoveryBackend.discoveryEvent().collect {
                println("Discovered: $it")
            }
        }

        launch {
            while (true) {
                discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
                delay(1500L)
            }
        }
    }

    //val discoveryBackend = UDPDiscoveryBackend()
    //discoveryBackend.open()

   /* val testModule = module {
        singleOf(::UDPDiscoveryBackend) { bind<DiscoveryBackend>() }
    }

    val application = startKoin {
        printLogger()
        modules(testModule)
    }

    val test = KoinJavaComponent.getKoin().get<>()*/

/*    runBlocking {
        launch {
            discoveryBackend.discoveryEvent().collect {
                println("Discovered: $it")
            }
        }

        discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
        delay(1500L)
        discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
        delay(1500L)
        discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
        delay(1500L)
        discoveryBackend.close()*/

    /*launch {
        while (true) {
            discoveryBackend.send(DefaultClientDiscovery("marek", "Hello World", 1234))
            delay(1500L)
        }
    }*/
    //}
}