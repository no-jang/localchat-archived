plugins {
    application

    kotlin("jvm") version "1.7.20"
}

val staticResource: Configuration by configurations.creating


dependencies {
    staticResource(projects.frontend)

    implementation("io.ktor:ktor-server-core:2.1.1")
    implementation("io.ktor:ktor-server-netty:2.1.1")
}

tasks {
    named<ProcessResources>("processResources") {
        from(staticResource) {
            into("static")
        }
    }
}
