package com.nstudiosappdev.stocker.dashboard.data

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.data.datasource.DataSource
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CurrenciesDataModule {

    @Provides
    @Singleton
    fun provideCurrenciesServices(retrofit: Retrofit) : CurrenciesServices=
        retrofit.create(CurrenciesServices::class.java)

    @Provides
    @Singleton
    fun provideCurrenciesRemoteDataSource(
        currenciesServices: CurrenciesServices,
        errorFactory: ErrorFactory
    ): DataSource.RemoteDataSource.FetchDataSource<Currencies> =
        CurrenciesRemoteDataSource(currenciesServices, errorFactory)

    @Provides
    @Singleton
    fun provideCurrenciesRepository(
        currenciesRemoteDataSource: DataSource.RemoteDataSource.FetchDataSource<Currencies>,
        @Named(CoroutineManagerModule.AM_NAME_REPOSITORY) asyncManager: AsyncManager
    ): CurrenciesRepository = CurrenciesRepositoryImpl(currenciesRemoteDataSource, asyncManager)


}