import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")

    id("io.gitlab.arturbosch.detekt")
}

// TODO Wait for issue https://github.com/gradle/gradle/issues/15383 to be fixed
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

dependencies {
    detektPlugins(libs.findLibrary("kotlin.gradle.detekt.formatting").get()) {
        exclude("org.slf4j", "slf4j-nop")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += listOf("-Xuse-k2")
    }
}