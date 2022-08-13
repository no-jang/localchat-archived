plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.core.common)

    api(libs.koin)
    api(libs.koin.annotations)

    implementation(libs.koin.slf4j)
}