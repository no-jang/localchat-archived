plugins {
    id("build-logic.kotlin")

    application
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.core.plugin)

    implementation(projects.plugins.test)
}