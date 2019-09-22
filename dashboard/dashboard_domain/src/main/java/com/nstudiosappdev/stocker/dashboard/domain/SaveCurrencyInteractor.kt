package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.date.DateTimeConverter
import com.nstudiosappdev.core.domain.BaseInteractor
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Named

class SaveCurrencyInteractor @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    private val errorFactory: ErrorFactory,
    @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager
) : BaseInteractor(asyncManager), Interactor.DeferredInteractor<SaveCurrencyInteractor.Params, Boolean>  {
    override suspend fun executeAsync(postParams: Params): Deferred<DataHolder<Boolean>> = handleAsync{
        val currency = Currency(
            bankName = postParams.currency.bankName,
            buyPrice = postParams.currency.buyPrice,
            buyStatus = postParams.currency.buyStatus,
            sellPrice = postParams.currency.sellPrice,
            sellStatus = postParams.currency.sellStatus,
            currencyType = postParams.currency.currencyType
        )
        return@handleAsync when (val response = currenciesRepository.saveCurrency(
            currency
        ).await()) {
            is DataHolder.Success -> DataHolder.Success(response.data)
            else -> DataHolder.Fail(errorFactory.createUnknownError())
        }
    }

    class Params(
        val currency: Currency
    ) : Interactor.Params()
}