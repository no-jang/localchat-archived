import org.gradle.api.internal.FeaturePreviews.Feature

rootProject.name = "localchat"

enableFeaturePreview(Feature.TYPESAFE_PROJECT_ACCESSORS.toString())
enableFeaturePreview(Feature.STABLE_CONFIGURATION_CACHE.toString())

pluginManagement {
    //includeBuild("gradle/build-logic")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("gradle/build.versions.toml"))
        }
    }
}

//include("backend:app")
//include("backend:common")
//include("backend:di")
//include("backend:discovery")
//include("backend:network")
//
//include("frontend")
//
//include("core:app")
//include("core:common")
//include("core:di")
//include("core:plugin")
//include("core:processor")
//
//include("plugins:test")
//
//include("web")
//
//include("test")