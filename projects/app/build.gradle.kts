plugins {
    id("build-logic.kotlin")
    application
}

val modules by configurations.creating

application {
    mainClass.set("de.localchat.MainKt")
}

dependencies {
    implementation(projects.projects.core)

    modules(projects.modules.web)
}

distributions {
    main {
        contents {
            from((modules - configurations.runtimeClasspath)) {
                into("modules")
            }
        }
    }
}
