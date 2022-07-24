plugins {
    application

    id("build-logic.kotlin")
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.backend.common)
    implementation(projects.backend.network)
    implementation(projects.backend.networkNetty)
}