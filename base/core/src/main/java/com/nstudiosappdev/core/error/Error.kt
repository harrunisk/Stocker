package com.nstudiosappdev.core.error

sealed class Error {

    data class UnknownError(val message: String) : Error()

    data class ApiError(val code: String, val message: String) : Error()

    data class ApiErrors(val errors: List<Error>) : Error()

    data class BusinessError(val code: Int = 1, val message: String? = null) : Error()

    data class InvalidResponseError(val message: String) : Error()

    data class ExceptionalError(val message: String?) : Error()

    class ConnectionError : Error() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class EmptyCacheResult : Error() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class UnhandledStateError : Error() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class AuthenticationError : Error() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class InvalidInteractorRequestError : Error() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }
}
