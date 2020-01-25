package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.data.db.StockerDb
import com.nstudiosappdev.core.data.db.entity.CurrenciesEntity
import com.nstudiosappdev.core.data.db.entity.DbEntityMapper
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRepository
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRequest
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
class CurrenciesDataModule {

    @Provides
    @Singleton
    fun provideCurrenciesServices(retrofit: Retrofit): CurrenciesServices =
        retrofit.create(CurrenciesServices::class.java)

    @Provides
    @Singleton
    fun provideCurrenciesRemoteDataSource(
        currenciesServices: CurrenciesServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.RequestDataSource<CurrenciesRequest, List<Currency>> =
        CurrenciesRemoteDataSource(currenciesServices, errorFactory)

    @Provides
    internal fun provideCurrenciesDbEntityMapper(): DbEntityMapper<CurrenciesEntity, Currency> =
        CurrenciesDbEntityMapper()

    @Provides
    @Singleton
    @Named(CURRENCIES_LOCAL)
    internal fun provideCurrenciesLocalDataSource(
        db: StockerDb,
        mapper: DbEntityMapper<CurrenciesEntity, Currency>
    ): DataSource.LocalDataSource<Long, Currency> =
        CurrenciesLocalDataSource(db, mapper)

    @Provides
    @Singleton
    fun provideCurrenciesRepository(
        currenciesRemoteDataSource: DataSource.RemoteDataSource.RequestDataSource<CurrenciesRequest, List<Currency>>,
        @Named(CURRENCIES_LOCAL) currenciesLocalDataSource: DataSource.LocalDataSource<Long, Currency>,
        @Named(CoroutineManagerModule.AM_NAME_REPOSITORY) asyncManager: AsyncManager
    ): CurrenciesRepository = CurrenciesRepositoryImpl(
        currenciesRemoteDataSource,
        currenciesLocalDataSource,
        asyncManager
    )

    companion object {
        const val CURRENCIES_LOCAL = "CurrenciesDataModuleLocal"
    }
}
