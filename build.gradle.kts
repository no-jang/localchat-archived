plugins {
    application
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
    // Web Server
    implementation("com.sparkjava:spark-core:2.9.3")

    // Networking
    implementation("io.projectreactor.netty:reactor-netty-core:1.0.20")
    implementation("io.projectreactor.netty:reactor-netty-http:1.0.20")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-core-modern:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")
}