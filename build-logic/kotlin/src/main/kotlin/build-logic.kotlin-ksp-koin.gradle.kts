plugins {
    id("build-logic.kotlin-ksp")
}

dependencies {
    //TODO Remove if pull request accepted ksp("io.insert-koin:koin-ksp-compiler:1.0.1")
    ksp(fileTree("libs/ksp"))
    ksp("io.insert-koin:koin-annotations:1.0.1")
}