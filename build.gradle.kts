plugins {
    application

    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("com.github.ben-manes.versions") version "0.42.0"
}

group = "de.localchat"
version = "0.1.0-alpha.1"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

val isDevelopment: Boolean = project.ext.has("development")

application {
    mainClass.set("de.localchat.MainKt")

    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-api-kotlin:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")

    // Web
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:2.0.3")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.0.3")
    implementation("io.ktor:ktor-server-core-jvm:2.0.3")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.0.3")
    implementation("io.ktor:ktor-server-host-common-jvm:2.0.3")
    implementation("io.ktor:ktor-server-locations-jvm:2.0.3")
    implementation("io.ktor:ktor-server-netty-jvm:2.0.3")
    implementation("io.ktor:ktor-server-status-pages-jvm:2.0.3")
    implementation("io.ktor:ktor-server-webjars-jvm:2.0.3")
    implementation("io.ktor:ktor-server-websockets-jvm:2.0.3")

    testImplementation("io.ktor:ktor-server-test-host:2.0.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.10")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0-RC1")
}