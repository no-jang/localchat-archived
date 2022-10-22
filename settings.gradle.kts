import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

include("modules:app")
include("modules:app:backend")
include("modules:app:frontend")
include("modules:core")
include("modules:core:backend")
include("modules:core:frontend")