package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.data.datasource.BaseDataSource
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRepository
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRequest
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val currenciesRemoteDataSource: DataSource.RemoteDataSource.RequestDataSource<CurrenciesRequest, List<Currency>>,
    private val currenciesLocalDataSource: DataSource.LocalDataSource<Long, Currency>,
    asyncManager: AsyncManager
) : BaseDataSource(asyncManager), CurrenciesRepository {

    override suspend fun getCurrencies(currenciesRequest: CurrenciesRequest): Deferred<DataHolder<List<Currency>>> = handleAsync {
        val result = currenciesRemoteDataSource.getResult(currenciesRequest)
        result
    }
}