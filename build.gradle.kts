plugins {
    application
}

repositories {
    mavenCentral() 
}

application {
    mainClass.set("de.localchat.Main")
}

dependencies {
    implementation("com.sparkjava:spark-core:2.9.3")
}