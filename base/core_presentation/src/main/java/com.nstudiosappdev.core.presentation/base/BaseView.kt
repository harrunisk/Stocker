package com.nstudiosappdev.core.presentation.base

import com.nstudiosappdev.core.error.Error

interface BaseView {
    fun onError(e: Error)
}