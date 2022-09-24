import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

// Enable features previews
enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())