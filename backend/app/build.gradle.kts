plugins {
    id("build-logic.kotlin")
}

dependencies {
    implementation(projects.backend.common)
    testFixturesImplementation(testFixtures(projects.backend.common))
}