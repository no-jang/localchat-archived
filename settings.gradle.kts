import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

// Enable features previews
enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

// Extract build logic into own include Build
pluginManagement {
    includeBuild("gradle/build-logic")
}

// Add a dependency catalog for dependencies used for executing the build
dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("gradle/build.versions.toml"))
        }
    }
}
