package com.functionalhub.kotlinday

import arrow.fx.IO
import arrow.fx.extensions.fx
import io.kotlintest.shouldBe
import kotlinx.coroutines.Dispatchers
import org.junit.Test

class SideEffectsTest {

    @Test
    fun `side effects on view`() {
        val view = Database()

        IO.fx {
            val data = loadState().bind()
            continueOn(Dispatchers.IO)
            effect { view.data = data }.bind()
        }.unsafeRunSync()

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