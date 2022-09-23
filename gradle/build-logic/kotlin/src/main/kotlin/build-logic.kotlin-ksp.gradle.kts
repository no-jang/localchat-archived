import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.google.devtools.ksp")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs - listOf("-Xuse-k2")
    }
}