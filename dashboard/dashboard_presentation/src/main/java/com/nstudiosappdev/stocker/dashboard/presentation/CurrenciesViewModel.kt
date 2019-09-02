package com.nstudiosappdev.stocker.dashboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nstudiosappdev.core.coroutines.CoroutineManager
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.viewmodel.BaseViewModel
import com.nstudiosappdev.stocker.dashboard.domain.Currencies
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import javax.inject.Inject
import javax.inject.Named

class CurrenciesViewModel @Inject constructor(
    @Named(CoroutineManagerModule.CM_VIEWMODEL) coroutineManager: CoroutineManager,
    private val getCurrenciesInteractor: Interactor.DeferredRetrieveInteractor<List<Currency>>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

    private val _currencies = MutableLiveData<DataHolder<List<Currency>>>()

    val currencies: LiveData<DataHolder<List<Currency>>>
        get() = _currencies

    init {
        fetchCurrencies()
    }

    fun fetchCurrencies() = handleLaunch(execution = {
        _currencies.value = DataHolder.Loading()
        _currencies.value = getCurrenciesInteractor.execute().await()
    }, error = {
        _currencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })
}