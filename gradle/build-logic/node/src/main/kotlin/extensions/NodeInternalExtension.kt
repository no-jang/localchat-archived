package extensions

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import services.NodeService

abstract class NodeInternalExtension {
    lateinit var service: NodeService
}
