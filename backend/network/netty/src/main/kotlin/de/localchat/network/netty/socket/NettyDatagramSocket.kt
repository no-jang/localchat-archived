package de.localchat.network.netty.socket

import de.localchat.network.netty.environment.NettyEnvironment
import de.localchat.network.socket.AbstractSocket
import io.netty5.bootstrap.Bootstrap
import io.netty5.channel.*
import io.netty5.channel.socket.DatagramChannel
import org.slf4j.LoggerFactory

class NettyDatagramSocket(
    name: String,
    port: Int,
    environment: NettyEnvironment,
    eventLoopGroup: EventLoopGroup
) : AbstractSocket<NettyEnvironment>(
    name,
    port,
    environment,
    LoggerFactory.getLogger(NettyDatagramSocket::class.java)
) {
    private val channel: DatagramChannel

    init {
        val bootstrap = Bootstrap()
            .option(ChannelOption.SO_REUSEADDR, true)
            .group(eventLoopGroup)
            .channelFactory(environment.newDatagramChannelFactory())
            .handler(object : ChannelInitializer<DatagramChannel>() {
                override fun initChannel(ch: DatagramChannel) {
                    ch.pipeline().addLast(object : ChannelHandler {
                        override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
                            // TODO
                            println("Channel read: $msg")
                        }
                    })
                }
            })

        channel = bootstrap
            .bind(port)
            .asStage()
            .get() as DatagramChannel
    }

    override fun doClose() {
        super.doClose()

        channel.close()
    }
}