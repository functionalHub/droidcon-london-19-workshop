package com.functionalhub.kotlinday

import arrow.core.Nel
import arrow.core.None
import arrow.core.Some
import arrow.core.extensions.nonemptylist.foldable.get
import io.kotlintest.shouldBe
import org.junit.Test

class NonEmptyListTest {

    @Test
    fun `head of non empty list`() {
        val list = Nel("a")

        list.get(0) shouldBe Some("a")
    }

    @Test
    fun `tail of non empty list`() {
        val list = Nel("a")

        list.get(1) shouldBe None
    }

}
