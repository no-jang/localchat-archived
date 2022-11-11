plugins {
    base

    id("build-logic.node-root")
}

node {
    tasks.set(listOf("build", "lint", "dev"))
}
