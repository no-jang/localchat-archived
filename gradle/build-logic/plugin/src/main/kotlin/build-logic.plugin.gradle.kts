plugins {
    id("build-logic.kotlin")
    id("build-logic.kotlin-annotations")
}

dependencies {
    api(project(":core:plugin"))
    ksp(project(":core:processor"))
}