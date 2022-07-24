import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "build-logic-test"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

include("app")
include("common")