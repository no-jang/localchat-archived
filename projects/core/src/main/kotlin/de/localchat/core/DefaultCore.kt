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

package de.localchat.core

import de.localchat.core.api.Core

/**
 * Test implementation of {@link Core}.
 */
class DefaultCore : Core {
    override fun print() {
        // println("Hello World!")
    }

    override fun test(): String = "Hello World!"
}
