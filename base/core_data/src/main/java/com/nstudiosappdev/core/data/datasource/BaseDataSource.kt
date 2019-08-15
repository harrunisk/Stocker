package com.nstudiosappdev.core.data.datasource

import com.nstudiosappdev.core.coroutines.AsyncManager

abstract class BaseDataSource(asyncManager: AsyncManager) : AsyncManager by asyncManager