import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        /**
         * Use the new kotlin compiler K2.
         * In other build-logic plugins this arg is remove because some compiler plugins are not compatible with K2.
         **/
        freeCompilerArgs = freeCompilerArgs + listOf("-Xuse-k2")
    }
}
