plugins {
    id("build-logic.kotlin")
}

dependencies {
    implementation(projects.backend.network.library)
    implementation(libs.bundles.netty)
    runtimeOnly(libs.bundles.netty.native)
}