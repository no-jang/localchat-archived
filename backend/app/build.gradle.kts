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
    application

    id("build-logic.kotlin")
}

application {
    mainClass.set("de.localchat.app.MainKt")
    applicationDefaultJvmArgs = listOf("--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED")
}

dependencies {
    implementation(projects.backend.common)
    implementation(projects.backend.network)
    implementation(projects.backend.networkNetty)
}
