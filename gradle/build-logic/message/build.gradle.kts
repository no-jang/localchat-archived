gradlePlugin {
    plugins {
        create("build-logic.message") {
            id = "build-logic.message"
            implementationClass = "de.localchat.gradle.message.MessagePlugin"
        }
    }
}
