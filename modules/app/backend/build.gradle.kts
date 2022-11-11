plugins {
  kotlin("jvm") version "1.7.21"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(projects.modules.core.backend)
}
