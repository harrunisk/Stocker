package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.data.datasource.BaseDataSource
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRepository
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRequest
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import javax.inject.Inject
import kotlinx.coroutines.Deferred

class CurrenciesRepositoryImpl @Inject constructor(
    private val currenciesRemoteDataSource: DataSource.RemoteDataSource.RequestDataSource<CurrenciesRequest, List<Currency>>,
    private val currenciesLocalDataSource: DataSource.LocalDataSource<Long, Currency>,
    asyncManager: AsyncManager
) : BaseDataSource(asyncManager), CurrenciesRepository {

    override suspend fun getCurrencies(currenciesRequest: CurrenciesRequest): Deferred<DataHolder<List<Currency>>> = handleAsync {
        val result = currenciesRemoteDataSource.getResult(currenciesRequest)
        result
    }

    override suspend fun getSavedCurrency(
        bankName: String,
        currencyType: String
    ): Deferred<DataHolder<Currency>> = handleAsync {
        val result = currenciesLocalDataSource.get(bankName, currencyType)
        return@handleAsync DataHolder.Success(result)
    }

    override suspend fun getSavedCurrencies(currencyType: String): Deferred<DataHolder<List<Currency>>> = handleAsync {
        val result = currenciesLocalDataSource.get(currencyType)
        return@handleAsync DataHolder.Success(result)
    }

    override suspend fun saveCurrency(
        currency: Currency
    ): Deferred<DataHolder<Boolean>> = handleAsync {
        val result = currenciesLocalDataSource.put(null, currency)
        return@handleAsync DataHolder.Success(result)
    }

    override suspend fun deleteCurrency(
        bankName: String,
        currencyType: String
    ): Deferred<DataHolder<Boolean>> = handleAsync {
        val result = currenciesLocalDataSource.remove(bankName, currencyType)
        return@handleAsync DataHolder.Success(result)
    }
}
