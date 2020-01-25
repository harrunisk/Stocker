package com.nstudiosappdev.core.data.adapter

import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.model.DataHolder
import javax.inject.Inject
import kotlinx.coroutines.Deferred

class ApiCallAdapter<T : Any> @Inject constructor(private val errorFactory: ErrorFactory) :
    CallAdapter<T> {

    override suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T> {
        val apiResult = apiCall.await()

        if (apiResult.data == null) {
            return DataHolder.Fail(errorFactory.createInvalidResponseError())
        }

        return DataHolder.Success(apiResult.data)
    }
}
