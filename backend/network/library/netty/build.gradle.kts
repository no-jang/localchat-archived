plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.backend.network.library)
    implementation(libs.bundles.netty)

    runtimeOnly(variantOf(libs.netty.native.epoll) { classifier("linux-x86_64") })
    runtimeOnly(variantOf(libs.netty.native.kqueue) { classifier("osx-x86_64") })

    testImplementation(testFixtures(projects.backend.common))
}