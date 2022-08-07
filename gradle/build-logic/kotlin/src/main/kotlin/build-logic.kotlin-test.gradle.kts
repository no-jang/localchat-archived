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
    id("build-logic.kotlin-base")

    `java-test-fixtures`

    id("org.jetbrains.kotlinx.kover")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    testFixturesImplementation(libs.findBundle("testing").get())
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    jvmArgs("--add-opens java.base/jdk.internal.misc=ALL-UNNAMED".split(" "))
}
