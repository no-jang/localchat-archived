plugins {
    id("build-logic.kotlin")

    application
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.backend.network.library.netty)
}