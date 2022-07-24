import org.gradle.api.internal.FeaturePreviews.Feature

pluginManagement {
    includeBuild("build-logic")
}

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

// Enable if required
//includeBuild("build-logic-test")

include("backend:app")
include("backend:common")