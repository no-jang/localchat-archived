package de.localchat.common

import de.localchat.common.environment.OperatingSystem
import io.kotest.core.test.Enabled
import io.kotest.core.test.EnabledOrReasonIf

val enabledIfWindows: EnabledOrReasonIf = {
    if (OperatingSystem.currentOS == OperatingSystem.WINDOWS) {
        Enabled.enabled
    } else {
        Enabled.disabled("Only enabled on Windows")
    }
}

val enabledIfLinux: EnabledOrReasonIf = {
    if (OperatingSystem.currentOS == OperatingSystem.LINUX) {
        Enabled.enabled
    } else {
        Enabled.disabled("Only enabled on Linux")
    }
}

val enabledIfMacOS: EnabledOrReasonIf = {
    if (OperatingSystem.currentOS == OperatingSystem.MACOS) {
        Enabled.enabled
    } else {
        Enabled.disabled("Only enabled on MacOS")
    }
}