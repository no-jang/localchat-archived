package org.pf4j.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration

class AnnotationProcessor(val codeGenerator: CodeGenerator) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val classes = resolver.getSymbolsWithAnnotation("Extension").map { it as KSClassDeclaration }
        val classNames = classes
            .map { it.qualifiedName?.asString() }
            .filterNotNull()

        val dependencies = Dependencies(false, *classes.map { it.containingFile!! }.toList().toTypedArray())
        val outputStream = codeGenerator
            .createNewFile(dependencies, "META-INF/services", "org.pf4j.Extension", "")
            .bufferedWriter()

        for (name in classNames) {
            outputStream.write(name)
            outputStream.newLine()
        }

        return listOf()
    }

    class AnnotationProcessorProvider : SymbolProcessorProvider {
        override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
            return AnnotationProcessor(environment.codeGenerator)
        }
    }
}