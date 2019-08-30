package com.nstudiosappdev.core.data.datasource

import androidx.annotation.CallSuper
import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.coroutines.DefaultAsyncManager
import com.nstudiosappdev.core.model.BaseRepository

abstract class BaseRepositoryImpl(protected val asyncManager: AsyncManager = DefaultAsyncManager()) : BaseRepository,
    AsyncManager by asyncManager {
    @CallSuper
    override fun dropRepo() {
        asyncManager.destroy()
    }
}