import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
    application
    idea

    kotlin("jvm") version "1.7.10"
    //kotlin("plugin.serialization") version "1.7.10"

    id("com.github.ben-manes.versions") version "0.42.0"

    //id("build-logic.kotlin-ksp-koin")
    //id("build-logic.protobuf")
}

allprojects {
    apply<KotlinPluginWrapper>()

    group = "de.localchat"
    version = "0.1.0-alpha.1"

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(18))
        }
    }
}

application {
    mainClass.set("de.localchat.Main")
}

dependencies {
    implementation(projects.subprojects.dependencyInjection)
    implementation(projects.subprojects.discovery)
    implementation(projects.subprojects.discoveryBackendUdp)
    implementation(projects.subprojects.frontendJavalin)
    implementation(projects.subprojects.networkNetty)
}