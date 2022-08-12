plugins {
    id("build-logic.kotlin")
    id("build-logic.kotlin-test")
}

dependencies {
    api(libs.kotlin.coroutines)
    api(libs.bundles.tinylog.api)

    implementation(libs.bundles.tinylog)
}