plugins {
    id("build-logic.kotlin-api")
    id("build-logic.kotlin-ksp")
}

dependencies {
    apiApi(libs.tinylog.api)
    apiApi(libs.tinylog.slf4j)

    implementation(libs.tinylog.impl)
}
