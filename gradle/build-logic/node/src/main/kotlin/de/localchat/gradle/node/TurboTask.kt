package de.localchat.gradle.node

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Input
import javax.inject.Inject

abstract class TurboTask : NodeTask() {
    @get:Inject
    abstract val providers: ProviderFactory

    @get:Input
    abstract val tasks: ListProperty<String>

    @get:Input
    abstract val workspaceRoot: DirectoryProperty

    @get:Input
    abstract val workspaceDirectory: DirectoryProperty

    init {
        args.addAll("turbo")
        args.addAll(tasks)
        args.add(providers.provider {
            val workspaceDir = workspaceDirectory.asFile.get()
            val workspaceRoot = workspaceRoot.asFile.get()
            val directory = workspaceDir.relativeTo(workspaceRoot)

            "--filter={./${directory}}..."
        })
    }
}
