package com.nstudiosappdev.stocker.dashboard.domain

import com.nstudiosappdev.core.coroutines.AsyncManager
import com.nstudiosappdev.core.domain.BaseInteractor
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Named

class GetCurrenciesInteractor  @Inject constructor(
    private val currenciesRepository: CurrenciesRepository,
    @Named(CoroutineManagerModule.AM_NAME_INTERACTOR) asyncManager: AsyncManager
) : BaseInteractor(asyncManager), Interactor.DeferredRetrieveInteractor<Currencies> {
    override suspend fun execute(): Deferred<DataHolder<Currencies>> = handleAsync {
        val result = currenciesRepository.getCurrencies().await()
        result
    }
}