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
    implementation("org.apache.spark:spark-core_2.13:3.3.0")
}