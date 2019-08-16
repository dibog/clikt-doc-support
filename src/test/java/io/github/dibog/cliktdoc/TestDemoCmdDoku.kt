package io.github.dibog.cliktdoc

import io.mockk.clearAllMocks
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class TestDemoCmdDoku : CliKtDocSupport {
    val cmd = DemoCmd()
    val baseArgs = listOf<String>()

    @BeforeEach
    fun clearMocks() {
        clearAllMocks()
    }

    // please don't change the block (also not the spacing, otherwise the asciidoc would look messy
    // tag::demo-command[]
    @Test
    fun testDemo() = document(                              // <1>
            listOf(),                                       // <2>
            "demo"                                          // <3>
    ) {
        // optional mock specs can go here                  // <4>
        // the block can be empty or be not there at all
    }
    // end::demo-command[]

    fun document(cmdArgs: List<String>, fragmentName: String, action: ()->Unit = {}) {
        document(cmd, baseArgs, cmdArgs, fragmentName, action)
    }
}