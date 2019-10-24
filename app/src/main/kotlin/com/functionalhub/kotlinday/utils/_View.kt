package com.functionalhub.kotlinday.utils

import android.view.View
import android.widget.TextView
import arrow.core.Option

fun <V : View, A> V.applyVisibility(option: Option<A>): V = apply {
  visibility = option.fold({ View.GONE }, { View.VISIBLE })
}

fun <V : TextView> V.applyText(option: Option<String>): V = apply {
  text = option.orNull()
}