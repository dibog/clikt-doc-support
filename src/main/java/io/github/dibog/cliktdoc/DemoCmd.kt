package io.github.dibog.cliktdoc

import com.github.ajalt.clikt.core.CliktCommand

class DemoCmd : CliktCommand("""
    Description for this command""".trimIndent(),
        name="demo-cmd") {
    override fun run() {
        println("Example demo output")
    }
}