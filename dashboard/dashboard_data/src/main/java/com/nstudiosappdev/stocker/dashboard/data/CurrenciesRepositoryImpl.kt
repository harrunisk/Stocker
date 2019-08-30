package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.data.datasource.BaseRepositoryImpl
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class CurrenciesRepositoryImpl @Inject constructor(
    private val currenciesRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<Currencies>
) : BaseRepositoryImpl(), CurrenciesRepository {

    override suspend fun getCurrencies(): Deferred<DataHolder<Currencies>> = handleAsync {
        val result = currenciesRemoteDataSource.fetch()
        result
    }
}