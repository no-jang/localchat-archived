package de.localchat.plugin.test

import de.localchat.plugin.TestExtensionPoint
import org.pf4j.Extension
import org.pf4j.Plugin
import org.pf4j.PluginWrapper
import org.slf4j.LoggerFactory

class TestPlugin(wrapper: PluginWrapper) : Plugin(wrapper) {
    private val logger = LoggerFactory.getLogger(TestPlugin::class.java)

    override fun start() {
        logger.info("Start test plugin")
    }

    override fun stop() {
        logger.info("Stop test plugin")
    }

    @Extension
    class TestExtension : TestExtensionPoint {
        private val logger = LoggerFactory.getLogger(TestExtension::class.java)

        override fun print() {
            logger.info("Test extension")
        }
    }
}