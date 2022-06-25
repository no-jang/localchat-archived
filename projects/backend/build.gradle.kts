plugins {
    id("java-basic")

    application
}

dependencies {
    implementation(libs.netty.http2)
}