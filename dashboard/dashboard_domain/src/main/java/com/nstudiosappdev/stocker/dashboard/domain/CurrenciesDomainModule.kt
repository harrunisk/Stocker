package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class CurrenciesDomainModule {

    @Provides
    fun provideGetCurrencies(
        @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager,
        errorFactory: ErrorFactory,
        currenciesRepository: CurrenciesRepository
    ): Interactor.DeferredInteractor<GetCurrenciesInteractor.Params, List<Currency>> =
            GetCurrenciesInteractor(currenciesRepository, errorFactory, asyncManager)

    @Provides
    fun provideGetSavedCurrency(
        @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager,
        errorFactory: ErrorFactory,
        currenciesRepository: CurrenciesRepository
    ): Interactor.DeferredInteractor<GetSavedCurrencyInteractor.Params, Currency> =
            GetSavedCurrencyInteractor(currenciesRepository, errorFactory, asyncManager)

    @Provides
    fun provideGetSavedCurrencies(
        @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager,
        errorFactory: ErrorFactory,
        currenciesRepository: CurrenciesRepository
    ): Interactor.DeferredInteractor<GetSavedCurrenciesInteractor.Params, List<Currency>> =
        GetSavedCurrenciesInteractor(currenciesRepository, errorFactory, asyncManager)

    @Provides
    fun provideSaveCurrency(
        @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager,
        errorFactory: ErrorFactory,
        currenciesRepository: CurrenciesRepository
    ): Interactor.DeferredInteractor<SaveCurrencyInteractor.Params, Boolean> =
        SaveCurrencyInteractor(currenciesRepository, errorFactory, asyncManager)

    @Provides
    fun provideDeleteCurrency(
        @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager,
        errorFactory: ErrorFactory,
        currenciesRepository: CurrenciesRepository
    ): Interactor.DeferredInteractor<DeleteCurrencyInteractor.Params, Boolean> =
        DeleteCurrencyInteractor(currenciesRepository, errorFactory, asyncManager)
}