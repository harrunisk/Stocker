package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.adapter.ApiCallAdapter
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRequest
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import java.lang.IllegalArgumentException
import javax.inject.Inject

class CurrenciesRemoteDataSource @Inject constructor(
    private val currenciesServices: CurrenciesServices,
    private val errorFactory: ErrorFactory
) : DataSource.RemoteDataSource.RequestDataSource<CurrenciesRequest,List<Currency>> {
    override suspend fun getResult(request: CurrenciesRequest): DataHolder<List<Currency>> {
        val callAdapter = ApiCallAdapter<List<Currency>>(errorFactory)
        when (request.currencyType){
            0 -> return callAdapter.adapt(currenciesServices.getUsdCurrencies())
            1 -> return callAdapter.adapt(currenciesServices.getEuroCurrencies())
            2 -> return callAdapter.adapt(currenciesServices.getGoldCurrencies())
            else -> throw IllegalArgumentException("Unknown position!")

        }
    }
}