plugins {
  kotlin("jvm") version "1.7.22"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(projects.modules.core.backend)
}
