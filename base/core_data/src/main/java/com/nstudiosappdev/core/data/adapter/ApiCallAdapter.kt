package com.nstudiosappdev.core.data.adapter

import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class ApiCallAdapter<T : Any>  @Inject constructor(private val errorFactory: ErrorFactory) : CallAdapter<T> {

    override suspend fun adapt(apiCall: Deferred<ApiResponse<T?>>): DataHolder<T> {
        val apiResult = apiCall.await()

        if (apiResult.code > 0) {
            val error = errorFactory.createApiError(apiResult.code, apiResult.message)
            return DataHolder.Fail(error)
        }

        if (apiResult.data == null) {
            return DataHolder.Fail(errorFactory.createInvalidResponseError())
        }

        return DataHolder.Success(apiResult.data)
    }
}