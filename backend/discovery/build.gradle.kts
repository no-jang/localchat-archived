plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.backend.common)

    implementation(libs.jmdns)
}