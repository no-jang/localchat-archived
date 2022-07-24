plugins {
    `kotlin-dsl` apply false
}

subprojects {
    apply<KotlinDslPlugin>()

    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    // TODO Enable if K2 supports plugins
    /*tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += listOf("-Xuse-k2")
        }
    }*/
}