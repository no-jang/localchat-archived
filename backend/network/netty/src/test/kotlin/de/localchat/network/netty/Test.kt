package de.localchat.network.netty


import de.localchat.common.enabledIfLinux
import de.localchat.common.enabledIfMacOS
import de.localchat.common.enabledIfWindows
import io.kotest.core.spec.style.FunSpec

class Test : FunSpec({
    test("testOnlyOnWindows").config(enabledOrReasonIf = enabledIfWindows) {
        println("Windows")
    }

    test("testOnlyOnLinux").config(enabledOrReasonIf = enabledIfLinux) {
        println("Linux")
    }

    test("testOnlyOnOsx").config(enabledOrReasonIf = enabledIfMacOS) {
        println("OSX")
    }
})