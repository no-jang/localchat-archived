dependencies {

}

gradlePlugin {
    plugins {
        create("build-logic.root") {
            id = "build-logic.root"
            implementationClass = "de.localchat.build.core.root.RootPlugin"
        }
    }
}
