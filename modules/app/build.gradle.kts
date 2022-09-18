plugins {
    application
    id("build-logic.kotlin")
}

application {
    mainClass.set("de.localchat.app.MainKt")
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

val runDir: File = layout.buildDirectory.dir("run").get().asFile
if (!runDir.exists()) runDir.mkdirs()

tasks {
    named<JavaExec>("run") {
        workingDir(runDir)
    }

    create<JavaExec>("runDev") {
        group = "application"

        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set(application.mainClass)
        workingDir(runDir)

        jvmArgs("-Dio.ktor.development=true")
    }
}