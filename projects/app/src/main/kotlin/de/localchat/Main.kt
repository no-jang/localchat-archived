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

package de.localchat

import de.localchat.core.api.module.Module
import java.net.URLClassLoader
import java.util.ServiceLoader

/**
 * Main application entry point.
 */
fun main() {
    //val serviceLoader = ServiceLoader.load(Module::class.java)
    //val modules = serviceLoader.toList()

    //println(modules.map { it::class.qualifiedName })

    val main = Main()
    main.test()
}

class Main {
    fun test() {
        val classLoader = this::class.java.classLoader as URLClassLoader
        classLoader.urLs.forEach { println(it) }
    }
}
