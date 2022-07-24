plugins {
    id("build-logic.kotlin")
}

dependencies {
    // Kotlin
    api(libs.kotlin.coroutines)

    // Logging
    api(libs.bundles.tinylog)
}