package com.functionalhub.kotlinday.utils

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(
  message: String,
  view: View = findViewById(android.R.id.content),
  duration: Int = Snackbar.LENGTH_INDEFINITE,
  extras: Snackbar.() -> Unit
) {
  Snackbar.make(view, message, duration).apply(extras).show()
}