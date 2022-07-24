plugins {
    id("build-logic.kotlin-analyses")

    alias(libs.plugins.versions)
}

allprojects {
    group = "de.localchat"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

detekt {
    source.from(fileTree("gradle") {
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/build")
        exclude("**/.gradle")
    })
}