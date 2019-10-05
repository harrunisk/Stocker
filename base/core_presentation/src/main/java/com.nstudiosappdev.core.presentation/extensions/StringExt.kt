package com.nstudiosappdev.core.presentation.extensions

import java.util.*

fun String.adjustSensitivityGiveString(sensitivity: Int): String {
    return String.format(Locale.US,"%.${sensitivity}f", this.replace(",", ".").toFloat())
}

fun String.adjustSensitivityGiveFloat(sensitivity: Int): Float {
    return String.format(Locale.US,"%.${sensitivity}f", this.replace(",", ".").toFloat()).toFloat()
}