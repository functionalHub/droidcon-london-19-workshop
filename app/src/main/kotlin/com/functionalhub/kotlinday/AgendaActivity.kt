package com.functionalhub.kotlinday

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import arrow.core.Either
import arrow.fx.IO
import arrow.fx.extensions.fx
import kotlinx.android.synthetic.main.simple_activity_agenda.*

class AgendaActivity : AppCompatActivity(R.layout.simple_activity_agenda) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IO.fx {
            effect { setupUI() }.bind()
        }.unsafeRunAsync { it.reportError() }
    }

    private fun setupUI() {
        buttonOne.setOnClickListener {
            IO.effect { updateMessage("One") }.unsafeRunAsync { it.reportError() }
        }
        buttonTwo.setOnClickListener {
            IO.effect { updateMessage("Two") }.unsafeRunAsync { it.reportError() }
        }
    }

    private fun updateMessage(message: String) {
        result.text = message
    }

}

fun Either<Throwable, Unit>.reportError() {
    mapLeft { e -> Log.d("AgendaActivity", e.message, e) }
}
