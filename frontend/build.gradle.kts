import com.github.gradle.node.pnpm.task.PnpmTask

plugins {
    id("com.github.node-gradle.node") version "3.4.0"
}

tasks {
    create<PnpmTask>("build") {
        group = "build"
        pnpmCommand.add("run")
        args.addAll(listOf("build"))

        val ignoredFiles = listOf(".next", ".turbo", "node_modules")
        val files = file("apps").listFiles() + file("packages").listFiles()

        files
            .filter { file -> file.isDirectory }
            .forEach { projectDir ->
                projectDir
                    .listFiles { _, name -> !ignoredFiles.contains(name) }
                    .forEach { file ->
                        if (file.isDirectory) {
                            inputs.dir(file)
                            outputs.dir(file)
                        } else {
                            inputs.file(file)
                            outputs.file(file)
                        }
                    }
            }

        outputs.cacheIf { true }
    }
}