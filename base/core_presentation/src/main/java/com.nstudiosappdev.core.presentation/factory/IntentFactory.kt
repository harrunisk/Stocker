package com.nstudiosappdev.core.presentation.factory

import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

interface IntentFactory {

    val context: WeakReference<Context>

    fun createShareIntent(packageName: String): Intent?

}