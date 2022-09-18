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
                api(projects.modules.web.api)

                // Web
                //implementation(libs.ktor.server.netty)
            }
        }
    }
}