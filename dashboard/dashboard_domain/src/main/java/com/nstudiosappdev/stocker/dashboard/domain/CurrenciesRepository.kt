package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred

interface CurrenciesRepository {
    suspend fun getCurrencies(currenciesRequest: CurrenciesRequest): Deferred<DataHolder<List<Currency>>>

    suspend fun getSavedCurrency(
        bankName: String,
        currencyType: String
    ): Deferred<DataHolder<Currency>>

    suspend fun getSavedCurrencies(currencyType: String): Deferred<DataHolder<List<Currency>>>

    suspend fun saveCurrency(currency: Currency): Deferred<DataHolder<Boolean>>

    suspend fun deleteCurrency(
        bankName: String,
        currencyType: String
    ): Deferred<DataHolder<Boolean>>
}
