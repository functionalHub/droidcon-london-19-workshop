package com.functionalhub.kotlinday.utils

import android.widget.ImageView
import arrow.core.Option
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

fun ImageView.applyImage(url: String, f: RequestBuilder<*>.() -> Unit = {}) =
  Glide.with(this).load(url).apply(f).into(this)

fun ImageView.applyImage(url: Option<String>, f: RequestBuilder<*>.() -> Unit = {}) =
  url.fold({ setImageDrawable(null) }, { applyImage(it, f) })

fun ImageView.applyRoundImage(url: String) =
  applyImage(url) { apply(RequestOptions.circleCropTransform()) }

fun ImageView.applyRoundImage(url: Option<String>) =
  applyImage(url) { apply(RequestOptions.circleCropTransform()) }