plugins {
    application
}

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
    // Web Server
    implementation("com.sparkjava:spark-core:2.9.3")

    // Networking
    implementation("io.netty:netty-transport:5.0.0.Alpha2")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-core-modern:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")
}