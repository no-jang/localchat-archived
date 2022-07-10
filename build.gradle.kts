plugins {
    application
    idea

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

application {
    mainClass.set("de.localchat.Main")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

dependencies {
    // TODO Remove if artifact is released
    implementation(fileTree("libs"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.3")
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Web Server
    implementation("io.javalin:javalin:4.6.4")

    //json
    implementation("org.json:json:20220320")

    //java to html
    implementation("com.j2html:j2html:1.5.0")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-api-kotlin:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")

    // Data
    implementation("com.google.protobuf:protobuf-java:3.21.1")

    // Network
    implementation("io.netty:netty5-handler:5.0.0.Alpha3")

    implementation("io.netty:netty5-transport-classes-epoll:5.0.0.Alpha3")
    implementation("io.netty:netty5-transport-classes-kqueue:5.0.0.Alpha3")
    runtimeOnly("io.netty:netty5-transport-native-epoll:5.0.0.Alpha3:linux-x86_64")
    runtimeOnly("io.netty:netty5-transport-native-kqueue:5.0.0.Alpha3:osx-x86_64")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0-RC1")
}