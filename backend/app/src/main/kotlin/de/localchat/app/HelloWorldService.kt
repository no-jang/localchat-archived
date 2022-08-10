package de.localchat.app

import GreeterGrpcKt
import HelloRequest
import helloReply

class HelloWorldService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest) = helloReply {
        message = "Hello ${request.name}"
    }
}