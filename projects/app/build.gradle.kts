plugins {
    id("build-logic.kotlin")
    application
}

application {
    mainClass.set("de.localchat.MainKt")
}

dependencies {
    implementation(projects.projects.core)

    implementation(projects.modules.web)
}
