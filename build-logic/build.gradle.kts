plugins {
    `kotlin-dsl` apply false
}

subprojects {
    apply<KotlinDslPlugin>()

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}