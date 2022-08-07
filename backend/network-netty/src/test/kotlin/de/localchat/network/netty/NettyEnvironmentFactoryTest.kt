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

package de.localchat.network.netty

import de.localchat.network.netty.environment.NettyEnvironmentFactory
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockkStatic
import io.netty5.channel.ChannelFactory
import io.netty5.channel.epoll.*

class NettyEnvironmentFactoryTest : FunSpec({
    test("CreateEpollNettyEnvironmentIfEpollIsAvailable") {
        mockkStatic(Epoll::class)

        Epoll.isAvailable() shouldBe true
        every { Epoll.isAvailable() } returns true

        val factory = NettyEnvironmentFactory()
        val environment = factory.newEnvironment()

        environment.newHandlerFactory().shouldBeInstanceOf<EpollHandler>()
        environment.newDatagramChannelFactory().shouldBeInstanceOf<ChannelFactory<EpollDatagramChannel>>()
        environment.newSocketChannelFactory().shouldBeInstanceOf<ChannelFactory<EpollSocketChannel>>()
        environment.newServerSocketChannelFactory().shouldBeInstanceOf<ChannelFactory<EpollServerSocketChannel>>()
    }
})