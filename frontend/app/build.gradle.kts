import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    id("com.github.node-gradle.node") version "3.4.0"
}

tasks {
    create<PnpmTask>("build") {
        group = "build"
        pnpmCommand.add("run")
        args.addAll(listOf("build"))
        inputs.files(fileTree(layout.projectDirectory) {
            exclude("**/.gradle/**")
            exclude("**/.next/**")
            exclude("**/.turbo/**")
            exclude("**/node_modules/**")
        })
        outputs.files(fileTree(layout.projectDirectory) {
            include("**/.next/server/**")
            include("**/.next/static/**")
        })
        outputs.cacheIf { true }
    }
}