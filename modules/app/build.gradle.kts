plugins {
    id("build-logic.kotlin")
    application
}

application {
    mainClass.set("de.localchat.MainKt")
}

dependencies {
    implementation(projects.modules.core)
}