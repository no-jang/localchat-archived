import com.google.protobuf.gradle.*

plugins {
    id("build-logic.kotlin")

    application
    idea

    id("com.google.protobuf") version "0.8.13"
}

application {
    mainClass.set("de.localchat.app.MainKt")
}

dependencies {
    implementation(projects.backend.common)
    implementation(projects.backend.network.netty)

    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.48.1")
    implementation("io.grpc:grpc-netty:1.48.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.4")
    implementation("org.jmdns:jmdns:3.5.8")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.2"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.48.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
            it.builtins {
                id("kotlin")
            }
        }
    }
}