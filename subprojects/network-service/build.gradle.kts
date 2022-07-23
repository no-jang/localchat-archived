dependencies {
    api(projects.subprojects.dependencyInjection)
    api(projects.subprojects.network)

    implementation(projects.subprojects.networkNetty)
}