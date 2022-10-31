import common.Constants
import extensions.NodeInternalExtension
import services.NodeService

val internalExtension = extensions.create(Constants.NODE_INTERNAL_EXTENSION, NodeInternalExtension::class.java)

internalExtension.service = NodeService()
