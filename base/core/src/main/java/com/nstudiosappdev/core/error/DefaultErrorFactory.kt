package com.nstudiosappdev.core.error

import javax.inject.Inject


class DefaultErrorFactory @Inject constructor() : ErrorFactory {

    override fun createApiError(code: String, messages: String) =
        Error.ApiError(code, messages)

    override fun createApiError(statusError: StatusError): Error {
        if (statusError.code == null) {
            return createUnknownError()
        }

        val message = statusError.message ?: "Hata oluştu."
        return createApiError(statusError.code, message)
    }

    override fun createErrors(errors: List<StatusError>?): Error {
        val safeErrorList = ArrayList<Error>()
        if (errors != null && errors.isNotEmpty()) {
            for (statusError in errors) {
                val apiError = createApiError(statusError)
                safeErrorList.add(apiError)
            }
        }

        return Error.ApiErrors(safeErrorList)
    }

    override fun createUnknownError(): Error = Error.UnknownError("Bilinmeyen Hata Oluştu.")

    override fun createErrorFromThrowable(t: Throwable) = Error.ExceptionalError(message = t.localizedMessage)

    override fun createInvalidResponseError() = Error.InvalidResponseError("Invalid Response!")

    override fun createUnHandledStateError() = Error.UnhandledStateError()

    override fun createInvalidInteractorRequestError() = Error.InvalidInteractorRequestError()

    override fun createAuthenticationError() = Error.AuthenticationError()

    override fun emptyCacheResultError() = Error.EmptyCacheResult()

    override fun createConnectionError() = Error.ConnectionError()

    override fun createBusinessError(code: Int, message: String?) = Error.BusinessError()

}