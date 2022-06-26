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
    implementation("io.netty:netty5-codec-http2:5.0.0.Alpha2")
}