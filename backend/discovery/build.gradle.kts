plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.backend.di)

    implementation(libs.jmdns)
}