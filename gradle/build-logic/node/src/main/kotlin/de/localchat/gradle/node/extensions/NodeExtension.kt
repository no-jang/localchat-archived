package de.localchat.gradle.node.extensions

import org.gradle.api.provider.ListProperty

abstract class NodeExtension {
    abstract val pipelines: ListProperty<String>
}
