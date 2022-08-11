plugins {
    id("build-logic.kotlin")
    id("build-logic.kotlin-annotations")
}

dependencies {
    api(projects.backend.common)

    api(libs.koin)
    api(libs.koin.annotations)

    implementation(libs.koin.slf4j)

    ksp(libs.koin.ksp)
}