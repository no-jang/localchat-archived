import org.gradle.api.internal.FeaturePreviews.Feature

pluginManagement {
    includeBuild("gradle/build-logic")
}

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

include("backend:app")
include("backend:common")
include("backend:di")
include("backend:discovery")
include("backend:network")

include("frontend")

include("core:app")
include("core:common")
include("core:di")
include("core:plugin")
include("core:processor")

include("plugins:test")

include("web")