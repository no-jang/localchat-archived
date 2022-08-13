import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf

plugins {
    id("build-logic.protobuf")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    implementation(libs.findBundle("grpc").get())
}

protobuf {
    plugins {
        id("grpc") {
            artifact = libs.findLibrary("grpc-gen-java").get().get().toString()
        }

        id("grpckt") {
            artifact = "${libs.findLibrary("grpc-gen-kotlin").get().get()}:jdk8@jar"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}