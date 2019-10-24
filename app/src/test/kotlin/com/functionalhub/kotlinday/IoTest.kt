package com.functionalhub.kotlinday

import arrow.fx.IO
import io.kotlintest.shouldBe
import kotlinx.coroutines.delay
import org.junit.Test

class IoTest {

    @Test(expected = RuntimeException::class)
    fun `concatenate operations with failure`() {
        `???`
    }

    @Test
    fun `concatenate operations with success`() {
        val result = `???`

        result shouldBe Invoice
    }

    @Test
    fun `concatenate operations with delay`() {
        val result = `???`

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