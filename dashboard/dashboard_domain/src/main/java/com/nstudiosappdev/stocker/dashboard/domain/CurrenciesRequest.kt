package com.nstudiosappdev.stocker.dashboard.domain

import com.google.gson.annotations.SerializedName

data class CurrenciesRequest(
    @SerializedName("currencyType") val currencyType: String
)
