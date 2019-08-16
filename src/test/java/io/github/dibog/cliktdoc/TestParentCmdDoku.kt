package io.github.dibog.cliktdoc

import io.mockk.clearAllMocks
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class TestParentCmdDoku : CliKtDocSupport {
    val cmd = ParentCmd()
    val baseArgs = listOf<String>()

    @BeforeEach
    fun clearMocks() {
        clearAllMocks()
    }

    @Test
    fun testParent() = document(listOf(), "parent")

    @Test
    fun testFirstChild() = document(listOf("first-child"), "first-child")

    @Test
    fun testSecondChild() = document(listOf("second-child","--req","required"), "second-child")

    fun document(cmdArgs: List<String>, fragmentName: String, action: ()->Unit = {}) {
        document(cmd, baseArgs, cmdArgs, fragmentName, action)
    }
}