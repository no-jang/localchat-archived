plugins {
    id("build-logic.kotlin")
}

dependencies {
    api(projects.backend.network)

    implementation(libs.bundles.netty)
    runtimeOnly(libs.bundles.netty.native)
}