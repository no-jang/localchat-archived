dependencies {
    implementation(projects.coreold)
}

gradlePlugin {
    plugins {
        create("build-logic.node-root") {
            id = "build-logic.node-root"
            implementationClass = "de.localchat.gradle.node.plugins.NodeRootPlugin"
        }

        create("build-logic.node") {
            id = "build-logic.node"
            implementationClass = "de.localchat.gradle.node.plugins.NodePlugin"
        }
    }
}
