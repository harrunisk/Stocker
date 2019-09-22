package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.domain.BaseInteractor
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Named

class GetSavedCurrencyInteractor @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val errorFactory: ErrorFactory,
    @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager
) : BaseInteractor(asyncManager), Interactor.DeferredInteractor<GetSavedCurrencyInteractor.Params, Currency> {

    override suspend fun executeAsync(postParams: Params): Deferred<DataHolder<Currency>> = handleAsync {
            return@handleAsync when (val response = currenciesRepository.getSavedCurrency(
                postParams.bankName,
                postParams.currencyType
            ).await()) {
                is DataHolder.Success -> DataHolder.Success(response.data)
                else -> DataHolder.Fail(errorFactory.createUnknownError())
            }
        }

    class Params(
        val bankName: String,
        val currencyType: String
    ) : Interactor.Params()
}