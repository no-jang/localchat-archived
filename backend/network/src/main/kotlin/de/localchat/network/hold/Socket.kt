package de.localchat.network.hold

import de.localchat.common.lifecycle.Closeable

interface Socket : Closeable {
    val name: String
    val port: Int
    val connections: List<Connection>
}