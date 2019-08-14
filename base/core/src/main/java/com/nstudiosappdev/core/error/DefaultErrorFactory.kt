package com.nstudiosappdev.core.error

import com.nstudiosappdev.core.error.Error.ApiError
import com.nstudiosappdev.core.error.Error.UnknownError
import com.nstudiosappdev.core.error.Error.AuthenticationError
import com.nstudiosappdev.core.error.Error.BusinessError
import com.nstudiosappdev.core.error.Error.InvalidResponseError


object DefaultErrorFactory : ErrorFactory {

    override fun createUnknownError(): Error = UnknownError("Bilinmeyen bir hata oluştu!")

    override fun createApiError(code: Int, message: String?): Error = ApiError(code, message)

    override fun createErrorFromThrowable(t: Throwable): Error = ApiError(message = t.localizedMessage)

    override fun createAuthenticationError(): Error = AuthenticationError()

    override fun createInvalidResponseError(): Error = InvalidResponseError("Invalid Response!")

    override fun createBusinessError(code: Int, message: String?): Error = BusinessError(code, message)

}