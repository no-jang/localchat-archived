package de.localchat.gradle.node.root

import org.gradle.api.provider.ListProperty

abstract class NodeRootExtension {
    abstract val tasks: ListProperty<String>
}
