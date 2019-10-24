package com.functionalhub.kotlinday

import arrow.fx.IO
import io.kotlintest.shouldBe
import org.junit.Test

class SideEffectsTest {

    @Test
    fun `side effects on view`() {
        val view = Database()

        view.data shouldBe "success"
    }

}

private fun loadState(): IO<String> =
    IO.just("success")

private class Database {

    var data: String = "initial"
        set(new) {
            check(Thread.currentThread().name != "main") { "Can't set data on main thread" }
            field = new
        }

}