plugins {
    id("build-logic.kotlin-analysis")
}

allprojects {
    group = "de.localchat"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

detekt {
    source.from(provider { fileTree(layout.projectDirectory) {
        include("*.kts")
        include("gradle/build-logic/**/*.kts")
        exclude("**/.gradle/**")
        exclude("**/build/**")
    }})
}
