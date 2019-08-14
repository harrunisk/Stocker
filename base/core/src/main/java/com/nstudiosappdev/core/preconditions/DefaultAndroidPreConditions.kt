package com.nstudiosappdev.core.preconditions

import java.lang.IllegalStateException

class DefaultAndroidPreConditions : AndroidPreConditions {
    override fun assertMainThread() {
        if (!Thread.currentThread().isMainThread()) {
            throw IllegalStateException("This code must be executed in main thread!")
        }
    }

    override fun assertUiThread() {
        if (!Thread.currentThread().isMainThread()) {
            throw IllegalStateException("This code must be executed in ui thread!")
        }
    }

    override fun assertWorkerThread() {
        if (Thread.currentThread().isMainThread()) {
            throw IllegalStateException("This code must be executed in ui thread!")
        }
    }

}