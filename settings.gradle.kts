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

// Add dependency catalog for dependencies used for the build process
dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("gradle/build.versions.toml"))
        }
    }
}

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
include(":modules:web")
include(":modules:web:api")
