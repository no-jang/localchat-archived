import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }

    includeBuild("build-logic")
}

include("subprojects:common")
include("subprojects:dependency-injection")
include("subprojects:discovery")
include("subprojects:discovery-backend-udp")
include("subprojects:frontend")
include("subprojects:frontend-javalin")
include("subprojects:network")
include("subprojects:network-netty")
include("subprojects:network-service")
include("subprojects:protobuf")