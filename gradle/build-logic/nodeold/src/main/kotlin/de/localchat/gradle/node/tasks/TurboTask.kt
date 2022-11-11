package de.localchat.gradle.node.tasks

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Input
import javax.inject.Inject

abstract class TurboTask : NpmTask() {
    @get:Inject
    abstract val providers: ProviderFactory

    @get:Input
    abstract val modules: ListProperty<String>

    init {
        args.addAll("turbo")
        args.add(providers.provider {
            "--filter=${modules.get().joinToString(",") {
                "$it..."
            }}"
        })
    }
}
