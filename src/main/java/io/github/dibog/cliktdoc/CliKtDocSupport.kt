package io.github.dibog.cliktdoc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.PrintHelpMessage
import org.junit.jupiter.api.fail
import java.io.BufferedWriter
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Paths

interface CliKtDocSupport {

    fun document(
            cmd: CliktCommand,
            baseArgs: List<String>,
            cmdArgs: List<String>,
            fragmentName: String,
            skipExec : Boolean = false,
            action: ()->Unit)
    {
        action()
        documentHelp(cmd, baseArgs, cmdArgs, fragmentName)
        documentOutput(cmd, baseArgs, cmdArgs, fragmentName, skipExec)
    }

    private fun documentHelp(cmd: CliktCommand, baseArgs: List<String>, cmdArgs: List<String>, fragmentName: String) {
        try {
            cmd.parse(baseArgs + cmdArgs + "--help")
            fail("Expected ${cmd.commandName} to fail")
        }
        catch(e: PrintHelpMessage) {
            val helpString =  e.command.getFormattedHelp()
            writer("help", fragmentName).use {
                it.writeLn("[source]")
                it.writeLn("----")
                it.writeLn(helpString)
                it.writeLn("----")
            }
        }
    }

    private fun documentOutput(cmd: CliktCommand, baseArgs: List<String>, cmdArgs: List<String>, fragmentName: String, skipExec: Boolean) {
        val args = (baseArgs+cmdArgs).joinToString(" ", transform = ::quote)

        writer("cmd", fragmentName).use {
            it.writeLn("${cmd.commandName} $args")
        }

        if(!skipExec) {
            val output = captureStdOut { cmd.parse(baseArgs + cmdArgs) }
            writer("output", fragmentName).use {

                it.writeLn("[source]")
                it.writeLn("----")
                it.writeLn(output)
                it.writeLn("----")
            }
        }
    }

    private fun quote(text: String) : String {
        return text
                .replace(" ", "\\ ")
                .replace("\\", "\\\\")
    }

    private fun writer(usage: String, fragmentName: String): BufferedWriter {
        val snippetDir = Paths.get("target/generated-cmd-snippets/$fragmentName").also { path ->
            Files.createDirectories(path)
        }
        return Files.newBufferedWriter(snippetDir.resolve("$usage.adoc"))
    }

    private fun BufferedWriter.writeLn(text: String) {
        write(text)
        newLine()
    }

    private fun captureStdOut(action: ()->Unit): String {
        val prevOut = System.out
        try {
            val bout = ByteArrayOutputStream()
            val out = PrintStream(bout)
            System.setOut(out)

            action()

            out.close()

            return String(bout.toByteArray(), Charsets.UTF_8)
        }
        finally {
            System.setOut(prevOut)
        }
    }
}