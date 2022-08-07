import org.gradle.api.internal.FeaturePreviews.Feature

pluginManagement {
    includeBuild("gradle/build-logic")
}

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

include("backend:app")
include("backend:common")