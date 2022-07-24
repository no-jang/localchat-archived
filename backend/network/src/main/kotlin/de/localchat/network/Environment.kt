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

package de.localchat.network

/**
 * This interface is intended to create networking resources that depends on the underlying platform (OS).
 * There should be one implementation for each platform.
 */
interface Environment {

    /**
     * This interface should be implemented by an enum class that contains all possible environments for the networking
     * backend.
     */
    interface Type {
        /**
         * The user-friendly name of the environment.
         */
        fun getName(): String
    }

    /**
     * Factory class to provide the supported environment types and create a new one.
     */
    interface Factory<T : Environment> {
        /**
         * Returns the list of supported environment types. These are ordered how recommended they are to be used.
         */
        fun checkSupportedTypes(): List<Type>

        /**
         * Creates a new environment of the given type.
         */
        fun newEnvironment(type: Type): T
    }

    /**
     * The [Type] of the environment.
     */
    fun getType(): Type
}