package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.coroutines.CoroutineManager
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.core.presentation.viewmodel.BaseViewModel
import com.nstudiosappdev.stocker.dashboard.domain.*
import javax.inject.Inject
import javax.inject.Named

class PortfolioViewModel @Inject constructor(
    @Named(CoroutineManagerModule.CM_VIEWMODEL) coroutineManager: CoroutineManager,
    private val getSavedCurrencyInteractor: Interactor.DeferredInteractor<GetSavedCurrencyInteractor.Params, Currency>,
    private val getSavedCurrenciesInteractor: Interactor.DeferredInteractor<GetSavedCurrenciesInteractor.Params, List<Currency>>,
    private val saveCurrencyInteractor: Interactor.DeferredInteractor<SaveCurrencyInteractor.Params, Boolean>,
    private val deleteCurrencyInteractor: Interactor.DeferredInteractor<DeleteCurrencyInteractor.Params, Boolean>,
    private val messagesListMapper: DisplayItemListMapper<Currency>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

}