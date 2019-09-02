package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.adapter.ApiCallAdapter
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import javax.inject.Inject

class CurrenciesRemoteDataSource @Inject constructor(
    private val currenciesServices: CurrenciesServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<List<Currency>> {
    override suspend fun fetch(): DataHolder<List<Currency>> {
        val currenciesAdapter = ApiCallAdapter<List<Currency>>(errorFactory)
        return currenciesAdapter.adapt(currenciesServices.getUsdCurrencies())
    }
}