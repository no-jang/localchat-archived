import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    base
    id("com.github.node-gradle.node") version "3.5.0"
}

node {
    download.set(false)
}

tasks {
    val make = create<PnpmTask>("make") {
        args.set(listOf("run", "build"))

        inputs.dir("src")
        inputs.dir("static")
        outputs.dir("dist")
        outputs.cacheIf { true }
    }

    getByName(BasePlugin.ASSEMBLE_TASK_NAME) {
        dependsOn(make)
    }
}
