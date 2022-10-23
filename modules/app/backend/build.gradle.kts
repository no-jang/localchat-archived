plugins {
  kotlin("jvm") version "1.7.20"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(projects.modules.core.backend)
}
