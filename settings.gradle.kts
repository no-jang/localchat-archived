import org.gradle.api.internal.FeaturePreviews.Feature

pluginManagement {
    includeBuild("gradle/build-logic")
}

plugins {
    id("com.gradle.enterprise") version "3.11.4"
}

rootProject.name = "localchat"

val isCI = providers.environmentVariable("CI").map { it.toBoolean() }.orElse(false)

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

include("modules:app")
include("modules:app:backend")
include("modules:app:frontend")
include("modules:core")
include("modules:core:backend")
include("modules:core:frontend")

if(isCI.isPresent) {
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlways()
        }
    }
}
