package com.nstudiosappdev.core.presentation.factory

import android.content.Context
import android.content.Intent
import java.lang.ref.WeakReference

class DefaultIntentFactory(override val context: WeakReference<Context>) :
    IntentFactory {

    override fun createShareIntent(packageName: String): Intent? =
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_TEXT,
                "https://play.google.com/store/apps/details?id=$packageName"
            )
        }

    override fun createShareTextIntent(shareBody: String, title: String): Intent {
        return Intent(Intent.ACTION_SEND)
            .apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareBody)
            }.also {
                Intent.createChooser(it, title)
            }
    }
}
