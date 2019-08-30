package com.nstudiosappdev.stocker.dashboard.domain

import com.google.gson.annotations.SerializedName

class Currencies (
    @SerializedName("GetPriceBankListResult") val currencies: List<Currency>
)