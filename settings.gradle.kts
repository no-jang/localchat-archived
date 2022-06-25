rootProject.name = "localchat"

pluginManagement {
    includeBuild("build-logic")
}

include("projects:backend")
include("projects:frontend-cli")