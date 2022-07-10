plugins {
    application

    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("com.google.protobuf") version "0.8.19"

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
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-api-kotlin:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")

    // Network
    implementation("io.netty:netty5-handler:5.0.0.Alpha3")
    implementation("io.netty:netty5-transport-classes-epoll:5.0.0.Alpha3")
    implementation("io.netty:netty5-transport-classes-kqueue:5.0.0.Alpha3")
    runtimeOnly("io.netty:netty5-transport-native-epoll:5.0.0.Alpha3:linux-x86_64")
    runtimeOnly("io.netty:netty5-transport-native-kqueue:5.0.0.Alpha3:osx-x86_64")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0-RC1")
}