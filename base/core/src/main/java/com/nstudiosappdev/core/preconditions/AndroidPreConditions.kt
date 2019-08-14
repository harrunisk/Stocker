package com.nstudiosappdev.core.preconditions

interface AndroidPreConditions {
    fun assertMainThread()

    fun assertUiThread()

    fun assertWorkerThread()
}