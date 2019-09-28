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

class GetCurrenciesInteractor  @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val errorFactory: ErrorFactory,
    @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager
) : BaseInteractor(asyncManager), Interactor.DeferredInteractor<GetCurrenciesInteractor.Params, List<Currency>> {

    override suspend fun executeAsync(postParams: Params): Deferred<DataHolder<List<Currency>>> = handleAsync {
        return@handleAsync when (val response = currenciesRepository.getCurrencies(
            CurrenciesRequest(
                currencyType = postParams.currencyType
            )).await()) {
            is DataHolder.Success -> DataHolder.Success(response.data)
            else -> DataHolder.Fail(errorFactory.createUnknownError())
        }
    }

    class Params(
        val currencyType: String
    ) : Interactor.Params()
}