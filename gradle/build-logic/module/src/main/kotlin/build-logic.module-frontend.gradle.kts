import de.localchat.gradle.turbo.TurboTask

plugins {
    id("build-logic.turbo")
}

tasks {
    register("turboBuild", TurboTask::class) {
        tasks.set(listOf("build"))
    }
}
