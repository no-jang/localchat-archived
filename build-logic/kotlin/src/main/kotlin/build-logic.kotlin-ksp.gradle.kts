plugins {
    kotlin("jvm")

    id("com.google.devtools.ksp")
}

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated/ksp/main/kotlin"))
        }
    }
}