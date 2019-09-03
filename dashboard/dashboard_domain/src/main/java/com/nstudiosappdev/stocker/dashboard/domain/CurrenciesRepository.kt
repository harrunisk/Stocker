package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred

interface CurrenciesRepository {
    suspend fun getCurrencies(currenciesRequest: CurrenciesRequest): Deferred<DataHolder<List<Currency>>>
}