plugins {
    id("build-logic.kotlin-base")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    testImplementation(libs.findBundle("testing").get())
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}