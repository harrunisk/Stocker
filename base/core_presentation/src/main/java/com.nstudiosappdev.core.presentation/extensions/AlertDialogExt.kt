package com.nstudiosappdev.core.presentation.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.nstudiosappdev.core.presentation.enums.DialogType
import com.nstudiosappdev.core.presentation.widget.CustomAlertDialog

/*
 * Creates custom alert dialog
 */
fun Context.createCustomAlertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    positiveButtonText: CharSequence? = null,
    negativeButtonText: CharSequence? = null,
    positiveButtonAction: (() -> Unit)? = null,
    negativeButtonAction: (() -> Unit)? = null,
    alertType: DialogType? = DialogType.WARNING,
    isAllCaps: Boolean = false
): AlertDialog = createCustomAlertDialogBuilder(
    title,
    message,
    positiveButtonText,
    negativeButtonText,
    positiveButtonAction,
    negativeButtonAction,
    alertType,
    isAllCaps
).create()

/*
 * Creates custom alert dialog builder
 */
fun Context.createCustomAlertDialogBuilder(
    title: CharSequence? = null,
    message: CharSequence? = null,
    positiveButtonText: CharSequence? = null,
    negativeButtonText: CharSequence? = null,
    positiveButtonAction: (() -> Unit)? = null,
    negativeButtonAction: (() -> Unit)? = null,
    alertType: DialogType? = DialogType.WARNING,
    isAllCaps: Boolean = false
): CustomAlertDialog {
    return CustomAlertDialog(this).apply {

        setIsAllCaps(isAllCaps)

        title?.let {
            setTitle(title)
        }
        message?.let {
            setMessage(message)
        }

        positiveButtonText?.let {
            setPositiveCustomButton(positiveButtonText) {
                positiveButtonAction?.invoke()
            }
        }

        negativeButtonText?.let {
            setNegativeCustomButton(negativeButtonText) {
                negativeButtonAction?.invoke()
            }
        }
        alertType?.let {
            setAlertType(alertType)
        }
    }
}
