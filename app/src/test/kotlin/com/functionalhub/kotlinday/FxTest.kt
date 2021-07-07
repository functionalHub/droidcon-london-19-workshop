package com.functionalhub.kotlinday

import arrow.core.*
import arrow.core.extensions.fx
import io.kotlintest.shouldBe
import org.junit.Test
import java.util.*

class FxTest {

    @Test
    fun `Option fx`() {
        val result = Option.fx {
            val conference = conference.bind()
            val venue = conference.venue.bind()
            venue.city.bind()
        }

        result shouldBe None
    }


    @Test
    fun `Either fx`() {
        val result: Either<ConferenceError, VenueAvailability> = Either.fx {
            val date = Conference.fetchDate().bind()
            Venue.fetchAvailability(date).bind()
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