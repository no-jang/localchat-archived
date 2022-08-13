import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.kotlin-base")

    kotlin("kapt")
}

kapt {
    showProcessorStats = true
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs.filter {
                it != "-Xuse-k2"
            }
        }
    }
}