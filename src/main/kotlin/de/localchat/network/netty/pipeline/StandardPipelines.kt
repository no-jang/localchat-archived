package de.localchat.network.netty.pipeline

import com.google.protobuf.MessageLite
import de.localchat.discovery.DiscoveryProtocol
import io.netty.contrib.handler.codec.protobuf.ProtobufDecoder
import io.netty.contrib.handler.codec.protobuf.ProtobufEncoder
import io.netty5.channel.ChannelPipeline
import io.netty5.handler.codec.DatagramPacketDecoder
import io.netty5.handler.codec.DatagramPacketEncoder

object StandardPipelines {
    fun ChannelPipeline.defaultProtobuf(message: MessageLite) {
        addLast(DatagramPacketDecoder(ProtobufDecoder(DiscoveryProtocol.DiscoveryRequest.getDefaultInstance())))
        addLast(DatagramPacketEncoder(ProtobufEncoder()))
    }
}