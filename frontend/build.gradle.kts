import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    base

    id("com.github.node-gradle.node") version "3.4.0"
}

node {
    download.set(false)
}

val buildWeb by tasks.registering(PnpmTask::class) {
    group = "build"
    args.set(listOf("run", "build"))

    inputs.dir("src")
    inputs.dir("static")

    outputs.dir("out")

    outputs.cacheIf { true }
}

tasks {
    named("build") {
        dependsOn(buildWeb)
    }

    named<Delete>("clean") {
        delete(".svelte-kit")
        delete("out")
    }
}

artifacts {
    add("default", buildWeb) {
        type = "jar"
        builtBy(buildWeb)
    }
}
