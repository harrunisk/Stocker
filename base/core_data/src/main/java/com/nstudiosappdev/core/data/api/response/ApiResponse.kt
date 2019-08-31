package com.nstudiosappdev.core.data.api.response

data class ApiResponse<T>(val code: String = "0", val message: String, val data: T?)