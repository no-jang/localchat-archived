import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

// TODO Wait for usage of dependency catalogues in precompiled script plugins
val build: VersionCatalog = extensions
    .getByType<VersionCatalogsExtension>()
    .named("build")

dependencies {
    detektPlugins(build.findLibrary("detekt-formatting").get())
}

detekt {
    parallel = true
}

tasks {
    val detekt = withType<Detekt> {
        reports {
            xml.required.set(true)
            sarif.required.set(true)
        }
    }

    named("lint") {
        dependsOn(detekt)
    }
}
