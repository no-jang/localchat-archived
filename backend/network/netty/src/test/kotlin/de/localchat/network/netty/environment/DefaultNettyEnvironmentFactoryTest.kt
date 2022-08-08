package de.localchat.network.netty.environment

import de.localchat.common.enabledIfLinux
import de.localchat.common.enabledIfMacOS
import de.localchat.common.enabledIfWindows
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.netty5.channel.epoll.Epoll
import io.netty5.channel.kqueue.KQueue

class DefaultNettyEnvironmentFactoryTest : FunSpec({
    test("EpollNettyEnvironmentShouldBeUsedIfEpollIsAvailable").config(enabledOrReasonIf = enabledIfLinux) {
        Epoll.isAvailable() shouldBe true

        DefaultNettyEnvironmentFactory()
            .newEnvironment()
            .shouldBeInstanceOf<EpollNettyEnvironment>()
    }

    test("KQueueNettyEnvironmentShouldBeUsedIfKQueueIsAvailable").config(enabledOrReasonIf = enabledIfMacOS) {
        KQueue.isAvailable() shouldBe true

        DefaultNettyEnvironmentFactory()
            .newEnvironment()
            .shouldBeInstanceOf<KQueue>()
    }

    test("NIONettyEnvironmentShouldBeUsedIfEpollAndKQueueIsNotAvailable").config(enabledOrReasonIf = enabledIfWindows) {
        DefaultNettyEnvironmentFactory()
            .newEnvironment()
            .shouldBeInstanceOf<NIONettyEnvironment>()
    }
})