package de.localchat.app

import GreeterGrpcKt
import helloRequest
import io.grpc.ManagedChannelBuilder
import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main() {
    val port = 4568

        val server: Server = ServerBuilder
            .forPort(4568)
            .addService(HelloWorldService())
            .build()

        server.start()

        val channel = ManagedChannelBuilder
            .forAddress("localhost", port)
            .usePlaintext()
            .build()

        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)

        coroutineScope {
            launch {
                val request = helloRequest { name = "World" }
                val response = stub.sayHello(request)
                println("Greeting: ${response.message}")
                delay(5000)
            }

            while (true) {
                delay(5000)
            }
        }

    //server.awaitTermination()

    /*val jmdns = JmDNS.create(InetAddress.getLocalHost())
    val serviceInfo = ServiceInfo.create("_http._tcp.local.", "example", 4589, "path=index.html")
    jmdns.registerService(serviceInfo)

    jmdns.addServiceListener("_http._tcp.local.", object : ServiceListener {
        override fun serviceAdded(event: ServiceEvent) {
            println("Service added: ${event.info.inet4Addresses}")
        }

        override fun serviceRemoved(event: ServiceEvent) {
            println("Service removed: ${event.info.inet4Addresses}")
        }

        override fun serviceResolved(event: ServiceEvent) {
            println("Service resolved: ${event.info.inet4Addresses}")
        }
    })*/
}