import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "build-logic"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

// Register both dependency catalogs from the parent build
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../libs.versions.toml"))
        }

        create("build") {
            from(files("../build.versions.toml"))
        }
    }
}

include("base")
include("kotlin")
