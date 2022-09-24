tasks {
    val lint by registering {
        group = "verification"
    }

    findByName("check")?.apply {
        dependsOn(lint)
    }
}
