package com.nstudiosappdev.core.data.adapter

import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred

interface CallAdapter<T : Any> {
    suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T>
}