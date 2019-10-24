package com.functionalhub.kotlinday

import arrow.core.*
import io.kotlintest.shouldBe
import org.junit.Test
import java.util.*

class FxTest {

    @Test
    fun `Option fx`() {
        val result =
            conference.flatMap { it.venue }
                .flatMap { it.city }

        result shouldBe None
    }


    @Test
    fun `Either fx`() {
        val result = Conference.fetchDate()
            .flatMap { date ->
                Venue.fetchAvailability(date)
            }

        result shouldBe ConferenceError.VenueNotAvailable.left()
    }

}

private val conference: Option<Conference> = Some(Conference)


private object Conference {

    val venue: Option<Venue> = None
    fun fetchDate(): Either<ConferenceError, Date> = Date().right()

}

private object Venue {

    val city: Option<String> = Some("London")
    fun fetchAvailability(date: Date): Either<ConferenceError, VenueAvailability> =
        ConferenceError.VenueNotAvailable.left()

}

private sealed class ConferenceError {
    object NoDateAvailable : ConferenceError()
    object VenueNotAvailable : ConferenceError()
}

private object VenueAvailability