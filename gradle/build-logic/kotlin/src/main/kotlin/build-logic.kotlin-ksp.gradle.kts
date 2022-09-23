import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("build-logic.kotlin")
    id("com.google.devtools.ksp")
}

sourceSets.all {
    val file = layout.buildDirectory.dir("generated/ksp/${name}/kotlin").get().asFile
    if (file.exists()) {
        java.srcDir(file)
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        // TODO Use K2 compiler if plugin support is added
        freeCompilerArgs = freeCompilerArgs - listOf("-Xuse-k2")
    }
}