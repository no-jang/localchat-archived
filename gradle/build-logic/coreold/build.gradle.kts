dependencies {

}

gradlePlugin {
    plugins {
        create("build-logic.module-root") {
            id = "build-logic.module-root"
            implementationClass = "de.localchat.gradle.core.plugins.ModuleRootPlugin"
        }

        create("build-logic.module") {
            id = "build-logic.module"
            implementationClass = "de.localchat.gradle.core.plugins.ModulePlugin"
        }
    }
}
