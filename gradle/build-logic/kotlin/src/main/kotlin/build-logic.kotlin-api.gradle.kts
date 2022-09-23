plugins {
    id("build-logic.kotlin")
}

sourceSets {
    create("api") {
        java {
            srcDir("src/api/java")
        }
    }

    named("main") {
        java {
            compileClasspath += sourceSets["api"].output
            runtimeClasspath += sourceSets["api"].output
        }
    }
}

java {
    registerFeature("api") {
        usingSourceSet(sourceSets["api"])
        withJavadocJar()
        withSourcesJar()
    }

    withJavadocJar()
    withSourcesJar()
}

tasks {
    named("build") {
        dependsOn(named("apiJar"))
    }
}