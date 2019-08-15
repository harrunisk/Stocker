package com.nstudiosappdev.core.domain

import com.nstudiosappdev.core.coroutines.AsyncManager

abstract class BaseInteractor constructor(asyncManager: AsyncManager) : AsyncManager by asyncManager