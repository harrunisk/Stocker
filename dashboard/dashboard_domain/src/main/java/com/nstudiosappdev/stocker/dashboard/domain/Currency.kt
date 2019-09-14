package com.nstudiosappdev.stocker.dashboard.domain

import com.google.gson.annotations.SerializedName

data class Currency (
    @SerializedName("bankName") val bankName: String?,
    @SerializedName("buyPrice") val buyPrice: String?,
    @SerializedName("buyStatus") val buyStatus: String?,
    @SerializedName("sellPrice") val sellPrice: String?,
    @SerializedName("sellStatus") val sellStatus: String?
)