import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.kotlin-base")

    id("com.google.devtools.ksp")
}

sourceSets {
    main {
        java {
            srcDirs("build/generated/ksp/main/kotlin")
        }
    }
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