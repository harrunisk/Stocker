package com.nstudiosappdev.core.data.api.response

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("GetPriceBankListResult") val data: T?
)
