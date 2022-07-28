/*
 * Copyright (C) 2022 Nojang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.localchat.network.pool

import de.localchat.common.lifecycle.Closeable
import de.localchat.network.socket.ClientSocket
import de.localchat.network.socket.DatagramSocket
import de.localchat.network.socket.ServerSocket

interface SocketPool : Closeable {
    fun connect(name: String, remoteAddress: String, port: Int): ClientSocket

    fun bind(name: String, port: Int): ServerSocket

    fun bindDatagram(name: String, port: Int): DatagramSocket
}