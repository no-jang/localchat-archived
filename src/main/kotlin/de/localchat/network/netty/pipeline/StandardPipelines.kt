package de.localchat.network.netty.pipeline

//import de.localchat.discovery.DiscoveryProtocol
import com.google.protobuf.MessageLite
import io.netty.contrib.handler.codec.protobuf.ProtobufEncoder
import io.netty5.channel.ChannelPipeline
import io.netty5.handler.codec.DatagramPacketEncoder

object StandardPipelines {
    fun ChannelPipeline.defaultProtobuf(message: MessageLite) {
        //addLast(DatagramPacketDecoder(ProtobufDecoder(DiscoveryProtocol.DiscoveryRequest.getDefaultInstance())))
        addLast(DatagramPacketEncoder(ProtobufEncoder()))
    }
}