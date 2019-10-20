package com.nstudiosappdev.navigation

import android.content.Intent
import com.nstudiosappdev.stocker.navigation.BuildConfig

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.PACKAGE_NAME, className)

internal fun String.loadIntentOrReturnNull(): Intent? =
    try {
        Class.forName(this).run { intentTo((this@loadIntentOrReturnNull)) }
    } catch (e: ClassNotFoundException) {
        null
    }
