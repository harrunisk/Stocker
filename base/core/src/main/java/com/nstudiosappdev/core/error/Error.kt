package com.nstudiosappdev.core.error


sealed class Error {

    data class UnknownError(val message: String) : Error()

    data class ApiError(val code: String, val message: String) : Error()

    data class ApiErrors(val errors: List<Error>) : Error()

    data class BusinessError(val code: Int = 1, val message: String? = null) : Error()

    data class InvalidResponseError(val message: String) : Error()

    data class ExceptionalError(val message: String?) : Error()

    class ConnectionError : Error()

    class EmptyCacheResult : Error()

    class UnhandledStateError : Error()

    class AuthenticationError : Error()

    class InvalidInteractorRequestError : Error()
}