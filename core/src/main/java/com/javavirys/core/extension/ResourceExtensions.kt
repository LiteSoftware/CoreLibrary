package com.javavirys.core.extension

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

val Resources.screenSize: Int
    get() = configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK

fun Resources.getDrawableCompat(@DrawableRes resId: Int, theme: Resources.Theme?) =
    ResourcesCompat.getDrawable(this, resId, theme)

@ColorInt
fun Resources.getColorCompat(@ColorRes resId: Int, theme: Resources.Theme?) =
    ResourcesCompat.getColor(this, resId, theme)

fun Resources.getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels

fun Resources.getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels

fun Context.getDrawableCompat(@DrawableRes resId: Int) = resources.getDrawableCompat(resId, theme)

@ColorInt
fun Context.getColorCompat(@ColorRes resId: Int) = resources.getColorCompat(resId, theme)

fun Fragment.getDrawableCompat(@DrawableRes resId: Int) = context?.getDrawableCompat(resId)

@ColorInt
fun Fragment.getColorCompat(@ColorRes resId: Int) = context?.getColorCompat(resId)