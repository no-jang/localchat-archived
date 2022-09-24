import org.gradle.api.internal.FeaturePreviews.Feature

// Extract build logic into own include Build
pluginManagement {
    includeBuild("gradle/build-logic")
}

plugins {
    // Apply this plugin to accept build scan terms of service
    id("com.gradle.enterprise") version "3.11.1"
}

rootProject.name = "localchat"

// Enable features previews
enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

// Automatically accept build scan terms of service only if in CI
if(System.getenv()["CI"] == "true") {
    gradleEnterprise {
        buildScan {
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
            publishAlways()
        }
    }
}

include(":modules:core")
include(":modules:core:api")
