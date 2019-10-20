package com.nstudiosappdev.core.presentation.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.nstudiosappdev.core.presentation.R
import com.nstudiosappdev.core.presentation.enums.DialogType

class CustomAlertDialog(
    context: Context
) : AlertDialog.Builder(context) {

    private var titleText: AppCompatTextView
    private var messageText: AppCompatTextView
    private var inputEditText: AppCompatEditText
    private var positiveButton: AppCompatButton
    private var negativeButton: AppCompatButton
    private var alertDialogButtonContainer: LinearLayout
    private var mPositiveClickListener: (() -> Unit)? = null
    private var mNegativeClickListener: (() -> Unit)? = null
    private var alertType: DialogType = DialogType.WARNING

    init {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.custom_alert_dialog_view, null)
        setView(mDialogView)
        titleText = mDialogView.findViewById(R.id.textViewTitle) as AppCompatTextView
        messageText = mDialogView.findViewById(R.id.textViewMessage) as AppCompatTextView
        inputEditText = mDialogView.findViewById(R.id.editTextMessage) as AppCompatEditText
        positiveButton = mDialogView.findViewById(R.id.positiveButton) as AppCompatButton
        negativeButton = mDialogView.findViewById(R.id.negativeButton) as AppCompatButton
        alertDialogButtonContainer = mDialogView.findViewById(R.id.alertDialogButtonContainer) as LinearLayout
    }

    override fun setTitle(title: CharSequence?): AlertDialog.Builder {
        titleText.text = title?.toString()
        return this
    }

    override fun setMessage(message: CharSequence?): AlertDialog.Builder {
        if (titleText.text.isNullOrBlank()) {
            titleText.visibility = View.GONE
        }
        messageText.text = message?.toString()
        return this
    }

    fun setPositiveCustomButton(text: CharSequence?, positiveButtonAction: (() -> Unit)? = null): (() -> Unit)? {
        positiveButton.apply {
            this.text = text?.toString()
            visibility = View.VISIBLE
        }
        mPositiveClickListener = positiveButtonAction
        return mPositiveClickListener
    }

    fun setNegativeCustomButton(text: CharSequence?, nagativeButtonAction: (() -> Unit)? = null): (() -> Unit)? {
        negativeButton.apply {
            this.text = text?.toString()
            visibility = View.VISIBLE
        }
        mNegativeClickListener = nagativeButtonAction
        return mPositiveClickListener
    }

    fun setAlertType(alertDialogType: DialogType) {
        alertType = alertDialogType
        when (alertDialogType) {
            DialogType.WARNING -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.VISIBLE
            }
            DialogType.ERROR -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.GONE
            }
            DialogType.INPUT -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.VISIBLE
                messageText.visibility = View.GONE
                inputEditText.visibility = View.VISIBLE
            }
            DialogType.INFO -> {
                alertDialogButtonContainer.visibility = View.VISIBLE
                positiveButton.visibility = View.VISIBLE
                negativeButton.visibility = View.GONE
            }
        }
    }

    fun setIsAllCaps(isAllCapsArg: Boolean) {
        positiveButton.let {
            positiveButton.isAllCaps = isAllCapsArg
        }
        negativeButton.let {
            negativeButton.isAllCaps = isAllCapsArg
        }
    }

    override fun create(): AlertDialog {
        val alertDialog = super.create()

        alertDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        if (alertType == DialogType.WARNING) {
            alertDialog.setCanceledOnTouchOutside(true)
        }

        positiveButton.setOnClickListener {
            alertDialog.dismiss()
            mPositiveClickListener?.invoke()
        }

        negativeButton.setOnClickListener {
            alertDialog.dismiss()
            mNegativeClickListener?.invoke()
        }
        return alertDialog
    }
}
