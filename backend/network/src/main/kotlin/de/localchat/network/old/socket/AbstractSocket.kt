/*
 * Copyright (C) 2022 Nojang
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package de.localchat.network.socket

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

abstract class AbstractSocket(
    private val name: String,
    private val port: Int
) : Socket {
    protected val closeFlow: MutableSharedFlow<Unit> = MutableSharedFlow()

    override fun getName(): String = name
    override fun getPort(): Int = port

    override fun close() {
        closeFlow.tryEmit(Unit)
    }

    override fun onClose(): Flow<Unit> = closeFlow
}