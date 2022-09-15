import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

pluginManagement {
    includeBuild("gradle/build-logic")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("gradle/build.versions.toml"))
        }
    }
}