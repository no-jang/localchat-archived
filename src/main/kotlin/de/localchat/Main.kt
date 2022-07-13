package de.localchat

fun main(args: Array<String>) {
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