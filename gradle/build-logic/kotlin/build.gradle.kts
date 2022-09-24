dependencies {
    implementation(projects.base)

    implementation(build.detekt)

    implementation(build.kotlin)
    implementation(build.kotlin.dokka)
    implementation(build.kotlin.validator)
}
