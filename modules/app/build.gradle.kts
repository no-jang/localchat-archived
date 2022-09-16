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
                implementation(projects.modules.core)
            }
        }
    }
}