plugins {
    id("build-logic.kotlin")
    id("build-logic.grpc")

    application
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.backend.common)

    implementation(libs.jmdns)
}