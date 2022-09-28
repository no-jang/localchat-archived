plugins {
    id("build-logic.kotlin-api")
    id("build-logic.kotlin-ksp")
}

dependencies {
    api(libs.tinylog.api)
    api(libs.tinylog.slf4j)
}
