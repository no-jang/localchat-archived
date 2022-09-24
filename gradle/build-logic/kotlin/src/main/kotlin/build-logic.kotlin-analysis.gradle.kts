import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("io.gitlab.arturbosch.detekt")
}

// TODO Wait for usage of dependency cataloges in precompiled script plugins
val build = extensions
    .getByType<VersionCatalogsExtension>()
    .named("build")

dependencies {
    detektPlugins(build.findLibrary("detekt-formatting").get())
}

detekt {
    parallel = true
}

tasks {
    withType<Detekt> {
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }
}
