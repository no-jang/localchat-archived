import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("build-logic.kotlin-jvm")

    alias(build.plugins.versions)
}

allprojects {
    group = "de.localchat"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

tasks {
    create<DependencyUpdatesTask>("dependencyUpdatesNightly") {
        revision = "integration"
        gradleReleaseChannel = "nightly"
    }
}