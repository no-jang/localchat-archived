plugins {
    id("build-logic.kotlin-api")
    id("build-logic.kotlin-ksp")
}

dependencies {
    apiApi(libs.hooks)
    ksp(libs.hooks.processor)
}