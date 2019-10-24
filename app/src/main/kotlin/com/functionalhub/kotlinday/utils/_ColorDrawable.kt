package com.functionalhub.kotlinday.utils

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun View.colorDrawable(@ColorRes colorRes: Int) =
  ColorDrawable(ContextCompat.getColor(context, colorRes))