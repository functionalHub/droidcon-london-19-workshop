package com.functionalhub.kotlinday

import arrow.core.Either
import arrow.core.Left
import io.kotlintest.shouldBe
import org.junit.Test

class EitherTest {

    @Test
    fun `network error`() {
        val result: Either<Failure, Success> = `???`

        result shouldBe Either.Left(Failure.Network)
    }

    @Test
    fun `parse error`() {
        val result: Either<Failure, Success> = `???`

        result shouldBe Either.Left(Failure.Parse)
    }

    @Test
    fun `success result`() {
        val result: Either<Failure, Success> = `???`

        result shouldBe Either.Right(Success)
    }

}

private fun successRequest(body: String = "valid"): Either<Failure, Response> =
    Either.right(Response(body)) // mention alternative

private fun failureRequest(): Either<Failure, Response> =
    Either.left(Failure.Network)

private fun parseRequest(response: Response): Either<Failure, Success> =
    Either.cond(test = response.body == "valid", ifTrue = { Success }, ifFalse = { Failure.Parse })

private class Response(val body: String)

private sealed class Failure {
    object Network: Failure()
    object Parse: Failure()
}

object Success
