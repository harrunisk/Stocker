package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CurrenciesServices {

    @GET("${END_POINT}${USD}")
    fun getUsdCurrencies() : Deferred<ApiResponse<List<Currency>?>>

    @GET("${END_POINT}${EURO}")
    fun getEuroCurrencies() : Deferred<ApiResponse<List<Currency>?>>

    @GET("${END_POINT}${GOLD}")
    fun getGoldCurrencies() : Deferred<ApiResponse<List<Currency>?>>

    companion object {
        const val END_POINT = "http://138.68.103.38:3000/currency_type="
        const val USD = "usd"
        const val EURO = "euro"
        const val GOLD = "gold"
    }
}
