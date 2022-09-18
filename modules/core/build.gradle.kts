plugins {
    id("build-logic.kotlin")
}

kotlin {
    jvm {
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                api(projects.modules.core.api)

                // Logging
                implementation(libs.tinylog.impl)
            }
        }
    }
}