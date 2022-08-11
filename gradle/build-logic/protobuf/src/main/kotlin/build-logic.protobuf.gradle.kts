import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    idea

    id("build-logic.kotlin-base")

    id("com.google.protobuf")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    implementation(libs.findLibrary("protobuf-kotlin").get())
}

protobuf {
    protoc {
        artifact = libs.findLibrary("protobuf-compiler").get().get().toString()
    }

    generateProtoTasks {
        all().forEach {
            it.builtins {
                id("kotlin")
            }
        }
    }
}