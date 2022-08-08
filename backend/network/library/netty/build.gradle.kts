plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.backend.network.library)
    implementation(libs.bundles.netty)
    implementation(libs.bundles.netty.native)

    testImplementation(testFixtures(projects.backend.common))
}