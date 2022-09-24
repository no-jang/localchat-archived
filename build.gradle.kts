allprojects {
    group = "de.localchat"
    version = "0.1.0"
}

// Accept terms of service for publishing a gradle build scan
extensions.findByName("gradleEnterprise")?.let {
    setProperty("buildScan.termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("buildScan.termsOfServiceAgree", "yes")
}
