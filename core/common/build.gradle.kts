plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(libs.kotlin.coroutines)
    api(libs.bundles.tinylog.api)

    implementation(libs.bundles.tinylog)
}