/*
 * Copyright 2022 Vitaliy Sychov
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

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