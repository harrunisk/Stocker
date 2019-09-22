package com.nstudiosappdev.core.preconditions

class DefaultAndroidPreConditions : AndroidPreConditions {
    override fun assertMainThread() {
        check(Thread.currentThread().isMainThread()) { "This code must be executed in main thread!" }
    }

    override fun assertUiThread() {
        check(Thread.currentThread().isMainThread()) { "This code must be executed in ui thread!" }
    }

    override fun assertWorkerThread() {
        check(!Thread.currentThread().isMainThread()) { "This code must be executed in ui thread!" }
    }

}