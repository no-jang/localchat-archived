import com.google.protobuf.gradle.GenerateProtoTask
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    java
    idea

    id("com.google.protobuf")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.2"
    }
}

dependencies {
    implementation("com.google.protobuf:protobuf-kotlin:3.21.2")
}

tasks {
    withType<GenerateProtoTask> {
        builtins {
            id("kotlin")
        }
    }
}