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
        inputs.dir("apps")
        inputs.dir("packages")
        outputs.dir("apps/docs/.next")
        outputs.dir("apps/web/.next")
        outputs.cacheIf { true }
    }
}