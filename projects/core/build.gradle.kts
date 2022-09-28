plugins {
    id("build-logic.kotlin-ksp")
}

dependencies {
    api(projects.projects.core.coreApi)

    implementation(libs.tinylog.impl)
}
