package com.functionalhub.kotlinday

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import arrow.core.Either
import arrow.fx.IO
import arrow.fx.extensions.fx
import kotlinx.android.synthetic.main.simple_activity_agenda.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay

class AgendaActivity : AppCompatActivity(R.layout.simple_activity_agenda) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IO.fx {
            effect { setupUI() }.bind()
        }.unsafeRunAsync { it.reportError() }
    }

    private fun setupUI() {
        buttonOne.setOnClickListener {
            IO.fx {
                effect { setLoading(true) }.bind()
                val message = Repository.loadOne().bind()
                continueOn(Dispatchers.Main)
                effect { setLoading(false) }.bind()
                effect { updateMessage(message) }.bind()
            }.unsafeRunAsync { it.reportError() }
        }
        buttonTwo.setOnClickListener {
            IO.fx {
                effect { setLoading(true) }.bind()
                val message = Repository.loadTwo().bind()
                continueOn(Dispatchers.Main)
                effect { setLoading(false) }.bind()
                effect { updateMessage(message) }.bind()
            }.unsafeRunAsync { it.reportError() }
        }
    }

    private fun updateMessage(message: String) {
        result.text = message
    }

    private fun setLoading(loading: Boolean) {
        if (loading) {
            loadingView.visibility = View.VISIBLE
            result.visibility = View.GONE
        } else {
            loadingView.visibility = View.GONE
            result.visibility = View.VISIBLE
        }
    }

}

object Repository {

    fun loadOne(): IO<String> = IO {
        delay(2_000)
        "one"
    }


    fun loadTwo(): IO<String> = IO {
        delay(2_000)
        "two"
    }

}

fun Either<Throwable, Unit>.reportError() {
    mapLeft { e -> Log.d("AgendaActivity", e.message, e) }
}
