package de.localchat.discovery.backend

//@Single(binds = [DiscoveryBackend::class])
//class UDPDiscoveryBackend : DiscoveryBackend {
// TODO Make this with a builder
/*private val udp = NettyMulticastUDPBootstrap("discovery")

private val discoveryFlow = MutableSharedFlow<ClientDiscovery>()

override fun open() {
    udp.port = MULTICAST_PORT
    udp.remoteAddress = MULTICAST_ADDRESS
    udp.pipeline = fun(pipeline) {
        pipeline.defaultProtobuf(CoreProtocol.registerAllExtensions().getDefaultInstance())
        pipeline.addLast(LengthFieldBasedFrameDecoder)
        pipeline.addLast(object : SimpleChannelInboundHandler<DiscoveryRequest>() {
            override fun messageReceived(ctx: ChannelHandlerContext?, msg: DiscoveryRequest) = runBlocking {
                val discovery = DefaultClientDiscovery(msg.name, msg.address, msg.port)
                Logger.trace("Received discovery request {}", discovery)
                discoveryFlow.emit(discovery)
            }
        })
    }

    Logger.debug("Open udp discovery backend")
    udp.bind()
    udp.joinMulticast()
}

override fun close() {
    Logger.debug("Close udp discovery backend")
    udp.close()
}

override fun send(discovery: ClientDiscovery) {
    Logger.trace("Send discovery request {}", discovery)
    udp.send(
        DiscoveryRequest.newBuilder()
            .setName(discovery.name)
            .setAddress(discovery.address)
            .setPort(discovery.port)
    )
}

override fun discoveryEvent(): Flow<ClientDiscovery> = discoveryFlow

companion object {
    const val MULTICAST_ADDRESS = "224.0.2.60"
    const val MULTICAST_PORT = 4445
}*/
//}