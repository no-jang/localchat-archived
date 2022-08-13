plugins {
    id("build-logic.kotlin-base")

    `java-test-fixtures`
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val testingBundle = libs.findBundle("kotest").get()

dependencies {
    testImplementation(testingBundle)
    testFixturesImplementation(testingBundle)
}

tasks {
    named<Test>(JavaPlugin.TEST_TASK_NAME) {
        useJUnitPlatform()
    }
}