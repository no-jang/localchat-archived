plugins {
    id("build-logic.kotlin")
    id("build-logic.kotlin-annotations")
}

dependencies {
    api(projects.backend.common)

    api(libs.koin)
    api(libs.koin.annotations)
    ksp(libs.koin.ksp)
}