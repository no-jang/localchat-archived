import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl` apply false
}

subprojects {
    apply<KotlinDslPlugin>()

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += listOf("-Xuse-k2")
        }
    }
}