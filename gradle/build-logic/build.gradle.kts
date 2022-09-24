plugins {
    `kotlin-dsl` apply false
}

subprojects {
    // All subprojects create gradle plugins used during the build
    apply<KotlinDslPlugin>()

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}
