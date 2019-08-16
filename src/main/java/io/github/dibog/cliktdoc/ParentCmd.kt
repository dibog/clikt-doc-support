package io.github.dibog.cliktdoc

import com.github.ajalt.clikt.core.NoRunCliktCommand
import com.github.ajalt.clikt.core.context
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.output.CliktHelpFormatter

class ParentCmd : NoRunCliktCommand("""
    Description for this command""".trimIndent(),
        name="parent")
{
    init {
        context {
            helpFormatter = CliktHelpFormatter(
                    showDefaultValues = true,
                    requiredOptionMarker = "*"
            )
        }

        subcommands(FirstChildCmd(), SecondChildCmd())
    }
}