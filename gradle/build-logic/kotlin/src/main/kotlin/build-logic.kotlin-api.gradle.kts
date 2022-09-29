import kotlinx.validation.KotlinApiBuildTask

plugins {
    id("build-logic.kotlin")

    id("org.jetbrains.kotlinx.binary-compatibility-validator")
}

val api by sourceSets.creating

sourceSets {
    main {
        java {
            compileClasspath += api.output
            runtimeClasspath += api.output
        }
    }
}

java {
    registerFeature("api") {
        withSourcesJar()
        withJavadocJar()
        usingSourceSet(api)
    }
}

tasks {
    named<KotlinApiBuildTask>("apiBuild") {
        inputClassesDirs = api.output
    }
}
