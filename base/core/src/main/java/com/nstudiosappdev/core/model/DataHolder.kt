package com.nstudiosappdev.core.model

import com.nstudiosappdev.core.error.Error

sealed class DataHolder<out T : Any> {

    data class Success<out T : Any>(val data: T) : DataHolder<T>()

    data class Fail(val e: Error) : DataHolder<Nothing>()

    object Loading : DataHolder<Nothing>()
}
