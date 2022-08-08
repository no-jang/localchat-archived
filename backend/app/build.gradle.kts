plugins {
    id("build-logic.kotlin")
}

dependencies {
    implementation(projects.backend.network.library.netty)
    testFixturesImplementation(testFixtures(projects.backend.common))
}