package com.functionalhub.kotlinday

import arrow.fx.IO
import arrow.fx.extensions.fx
import io.kotlintest.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import org.junit.Test

class IoTest {

    @Test(expected = RuntimeException::class)
    fun `concatenate operations with failure`() {
        IO.fx {
            val order = fetchOrder().bind()
            order.createInvoiceFailing().bind()
        }.unsafeRunSync()
    }

    @Test
    fun `concatenate operations with success`() {
        val result = IO.fx {
            val order = fetchOrder().bind()
            order.createInvoice().bind()
        }.unsafeRunSync()

        result shouldBe Invoice
    }

    @Test
    fun `concatenate operations with delay`() {
        val result = IO.fx {
            continueOn(Dispatchers.IO)
            println(Thread.currentThread().name)
            val order = fetchOrder().bind()
            println(Thread.currentThread().name)
            order.createInvoiceDelayed().bind()
        }.unsafeRunSync()

        result shouldBe Invoice
    }

}

private fun fetchOrder(): IO<Order> = IO { Order }

object Order {

    fun createInvoice() = IO.just(Invoice)

    fun createInvoiceDelayed() = IO {
        delay(1_000)
        Invoice
    }

    fun createInvoiceFailing(): IO<Invoice> = IO.raiseError(expectedFailure)

}

val expectedFailure = RuntimeException()

object Invoice