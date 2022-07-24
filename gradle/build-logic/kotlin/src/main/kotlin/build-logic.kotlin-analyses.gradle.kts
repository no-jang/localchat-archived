/*
 * Copyright (C) 2022 Nojang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

plugins {
    id("io.gitlab.arturbosch.detekt")
}

// TODO Wait for issue https://github.com/gradle/gradle/issues/15383 to be fixed
val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

detekt {
    parallel = true

    config.from(rootProject.layout.projectDirectory.file("config/detekt/detekt.yml"))
    source.from(
        fileTree(layout.projectDirectory) {
            include("**/*.kts")
            exclude("**/.gradle")
            exclude("**/build")
            exclude("**/src")
        }
    )
}

dependencies {
    detektPlugins(libs.findLibrary("kotlin.gradle.detekt.formatting").get()) {
        exclude("org.slf4j", "slf4j-nop")
    }
}
