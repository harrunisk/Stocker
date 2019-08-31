package com.nstudiosappdev.core.data.datasource

import androidx.annotation.CallSuper
import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.coroutines.DefaultAsyncManager
import com.nstudiosappdev.core.model.BaseRepository

abstract class BaseDataSource(asyncManager: AsyncManager) : AsyncManager by asyncManager