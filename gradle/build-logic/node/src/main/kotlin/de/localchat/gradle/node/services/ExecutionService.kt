package de.localchat.gradle.node.services

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.process.ExecOperations
import org.gradle.process.ExecResult
import javax.inject.Inject

abstract class ExecutionService @Inject constructor(private val exec: ExecOperations) :
    BuildService<ExecutionService.Params> {
    abstract class Params : BuildServiceParameters {
        abstract val workingDir: DirectoryProperty
    }

    fun execute(cmd: String, args: List<String>): ExecResult =
        exec.exec {
            commandLine(cmd)
            args(args)
            workingDir(parameters.workingDir)
        }
}
