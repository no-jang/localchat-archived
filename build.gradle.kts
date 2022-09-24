import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
    id("build-logic.base")
    id("build-logic.kotlin-analysis")
}

allprojects {
    group = "de.localchat"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }
}

// Check gradle kts files, too
//detekt {
//    source.from(provider { fileTree(layout.projectDirectory) {
//        include("*.kts")
//        include("gradle/build-logic/**/*.kts")
//        exclude("**/.gradle/**")
//        exclude("**/build/**")
//    }})
//}

// Merge detekt reports from all modules
val detektMergeSarif by tasks.registering(ReportMergeTask::class) {
    group = "verification"
    output.set(layout.buildDirectory.file("reports/detekt/merge.sarif"))
}

val detektMergeXml by tasks.registering(ReportMergeTask::class) {
    group = "verification"
    output.set(layout.buildDirectory.file("reports/detekt/merge.xml"))
}

// Merge all detekt reports into one file
subprojects {
    plugins.withType<DetektPlugin> {
        tasks.withType<Detekt> detekt@{
            finalizedBy(detektMergeSarif)
            finalizedBy(detektMergeXml)

            detektMergeSarif.configure {
                input.from(this@detekt.sarifReportFile)
            }

            detektMergeXml.configure {
                input.from(this@detekt.xmlReportFile)
            }
        }
    }
}
