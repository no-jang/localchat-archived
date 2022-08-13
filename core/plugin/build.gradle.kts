plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.core.di)

    api(libs.hooks)
    api(libs.pf4j)
}