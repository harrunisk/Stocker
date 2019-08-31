package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.adapter.ApiCallAdapter
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import javax.inject.Inject

class CurrenciesRemoteDataSource @Inject constructor(
    private val currenciesServices: CurrenciesServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.FetchDataSource<Currencies> {
    override suspend fun fetch(): DataHolder<Currencies> {
        val currenciesAdapter = ApiCallAdapter<Currencies>(errorFactory)
        return currenciesAdapter.adapt(currenciesServices.getUsdCurrencies())
    }
}