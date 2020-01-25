package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.domain.BaseInteractor
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import javax.inject.Inject
import javax.inject.Named
import kotlinx.coroutines.Deferred

class DeleteCurrencyInteractor @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val errorFactory: ErrorFactory,
    @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager
) : BaseInteractor(asyncManager),
    Interactor.DeferredInteractor<DeleteCurrencyInteractor.Params, Boolean> {

    override suspend fun executeAsync(postParams: Params): Deferred<DataHolder<Boolean>> =
        handleAsync {

            return@handleAsync when (val response = currenciesRepository.deleteCurrency(
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
