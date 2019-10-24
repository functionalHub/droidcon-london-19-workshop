package com.functionalhub.kotlinday

import arrow.core.*
import arrow.core.extensions.either.applicative.applicative
import arrow.core.extensions.list.monadFilter.filterMap
import arrow.core.extensions.list.traverse.sequence
import arrow.core.extensions.list.traverse.traverse
import arrow.core.extensions.option.applicative.applicative
import io.kotlintest.shouldBe
import org.junit.Test

class ListKTest {

    @Test
    fun `mapFilter with other data types`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        val evenNumbers = numbers.filterMap(isEven)

        evenNumbers shouldBe listOf(2, 4, 6, 8)
    }

    @Test
    fun `traverse a list to Option`() {
        val list = listOf("a", "1", "c")

        val result = list.traverse(Option.applicative()) { item ->
            item.toOption().mapNotNull { it.toIntOrNull() }
        }

        result shouldBe None

    }


    @Test
    fun `sequence a list to Either`() {
        val list = listOf("a".left(), 1.right(), "c".left())

        val result = list.sequence(Either.applicative())

        result shouldBe Left("a")
    }

}

private val isEven: (Int) -> Option<Int> =
    { number -> number.toOption().filter { it % 2 == 0 }  }
