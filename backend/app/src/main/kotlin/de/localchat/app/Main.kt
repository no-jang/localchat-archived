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
    val request = helloRequest { name = "World" }

    coroutineScope {
        launch {
            val response = stub.sayHello(request)
            println("Greeting: ${response.message}")
            delay(5000)
        }
    }

    server.awaitTermination()
}