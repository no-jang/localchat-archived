plugins {
    kotlin("multiplatform")
}

kotlin {
    linuxX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {

            }
        }
    }

    mingwX64 {
        binaries {
            executable(listOf(DEBUG, RELEASE)) {

            }
        }
    }


    jvm()
    js(IR) {
        browser()
    }
}