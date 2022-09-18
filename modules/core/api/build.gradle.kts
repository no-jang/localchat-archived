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
                // Logging
                api(libs.tinylog.api)
                api(libs.tinylog.slf4j)
            }
        }
    }
}