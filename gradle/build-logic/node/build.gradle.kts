gradlePlugin {
    plugins {
        create("build-logic.node") {
            id = "build-logic.node"
            implementationClass = "de.localchat.gradle.node.NodePlugin"
        }

        create("build-logic.node-root") {
            id = "build-logic.node-root"
            implementationClass = "de.localchat.gradle.node.NodeRootPlugin"
        }

        create("build-logic.turbo") {
            id = "build-logic.turbo"
            implementationClass = "de.localchat.gradle.turbo.TurboPlugin"
        }
    }
}
