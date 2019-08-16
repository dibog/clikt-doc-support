package io.github.dibog.cliktdoc

import com.github.ajalt.clikt.core.CliktCommand

class FirstChildCmd : CliktCommand("""
    Description for this command""".trimIndent(),
        name="first-child") {
    override fun run() {
        println("Example child output")
    }
}