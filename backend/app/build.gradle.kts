plugins {
    id("build-logic.kotlin")
    id("build-logic.grpc")

    application
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.backend.discovery)

    implementation(libs.jmdns)
}