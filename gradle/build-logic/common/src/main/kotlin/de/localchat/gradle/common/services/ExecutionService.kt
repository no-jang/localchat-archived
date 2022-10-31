package de.localchat.gradle.common.services

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.gradle.process.ExecOperations
import org.gradle.process.ExecResult
import org.gradle.workers.WorkQueue
import java.io.File
import javax.inject.Inject

abstract class ExecutionService @Inject constructor(private val exec: ExecOperations) : BuildService<ExecutionService.Params> {
    abstract class Params : BuildServiceParameters {
        abstract val workingDir: DirectoryProperty
        abstract val executable: Property<String>
        abstract val cmd: Property<String>
    }

    fun execute(args: List<String>, cmd: Provider<String> = parameters.cmd,
                executable: Provider<String> = parameters.executable): ExecResult =
        exec.exec {
            commandLine(executable.get(), cmd.get())
            args(args)
            workingDir(parameters.workingDir)
        }
}
