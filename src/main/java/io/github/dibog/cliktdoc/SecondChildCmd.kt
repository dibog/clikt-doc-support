package io.github.dibog.cliktdoc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class SecondChildCmd : CliktCommand("""
    Description for this command""".trimIndent(),
        name="second-child")
{
    val req by option(help="required argument").required()
    val opt by option(help="optional argument")
    val withDefault by option(help="argument with default").default("default-value")

    override fun run() {
        println("Example child output")
    }
}