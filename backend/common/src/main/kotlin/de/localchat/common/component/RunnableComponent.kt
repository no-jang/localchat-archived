package de.localchat.common.component

import de.localchat.common.lifecycle.Startable
import de.localchat.common.lifecycle.Stoppable

interface RunnableComponent : Component, Startable, Stoppable