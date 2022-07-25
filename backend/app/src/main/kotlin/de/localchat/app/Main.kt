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

package de.localchat.app

import de.localchat.network.netty.udp.NettyUDPPool
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Entry point for the application.
 */
fun main() {
    runBlocking {
        val pool = NettyUDPPool("pool")
        val socket = pool.bind("socket", 1234)

        launch {
            delay(5000)
            pool.close()
        }
    }
}
