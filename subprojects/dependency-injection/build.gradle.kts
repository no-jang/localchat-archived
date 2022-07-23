plugins {
    id("build-logic.kotlin-ksp-koin")
}

dependencies {
    api(projects.subprojects.common)

    api("io.insert-koin:koin-core:3.2.0")
    api("io.insert-koin:koin-annotations:1.0.1")
    api("io.insert-koin:koin-logger-slf4j:3.2.0")

    testApi("io.insert-koin:koin-test:3.2.0")
}