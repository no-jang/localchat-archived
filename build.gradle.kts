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
    id("build-logic.kotlin-analyses")

    alias(libs.plugins.versions)
}

allprojects {
    group = "de.localchat"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

detekt {
    source.from(
        fileTree("gradle") {
            include("**/*.kt")
            include("**/*.kts")
            exclude("**/build")
            exclude("**/.gradle")
        }
    )
}
