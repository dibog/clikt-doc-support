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

    @Test
    fun testDemo() = document(listOf(), "normal") {

    }

    fun document(cmdArgs: List<String>, fragmentName: String, action: ()->Unit = {}) {
        document(cmd, baseArgs, cmdArgs, fragmentName, action)
    }
}