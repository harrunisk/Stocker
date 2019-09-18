package com.nstudiosappdev.core.presentation.extensions

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nstudiosappdev.core.presentation.R

fun Context.showMessage(
    message: String,
    title: String,
    positiveButtonText: String,
    negativeButtonText: String
): MaterialAlertDialogBuilder {
    return MaterialAlertDialogBuilder(this, R.style.materialAlertDialogTheme).apply {
        setTitle(title)
        setMessage(message)
        setCancelable(true)
        setPositiveButton(positiveButtonText, null)
        setNegativeButton(negativeButtonText, null)
        show()
    }
}

