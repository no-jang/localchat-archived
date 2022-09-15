import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    alias(build.plugins.versions)

    id("build-logic.kotlin-jvm")
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