package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nstudiosappdev.core.coroutines.CoroutineManager
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.core.presentation.viewmodel.BaseViewModel
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import com.nstudiosappdev.stocker.dashboard.domain.GetCurrenciesInteractor
import javax.inject.Inject
import javax.inject.Named

class CurrenciesViewModel @Inject constructor(
    @Named(CoroutineManagerModule.CM_VIEWMODEL) coroutineManager: CoroutineManager,
    private val getCurrenciesInteractor: Interactor.DeferredInteractor<GetCurrenciesInteractor.Params, List<Currency>>,
    private val currenciesListMapper: DisplayItemListMapper<Currency>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

    private val _currencies = MutableLiveData<DataHolder<List<DisplayItem>>>()

    val currencies: LiveData<DataHolder<List<DisplayItem>>>
        get() = _currencies

/*    init {
        fetchCurrencies()
    }*/

    fun fetchCurrencies(
        currencyType: Int
    ) = handleLaunch(execution = {
        _currencies.value = DataHolder.Loading()
        val currenciesParams = GetCurrenciesInteractor.Params(
            currencyType = currencyType
        )

        val currenciesResult = getCurrenciesInteractor.execute(currenciesParams).await()
        if(currenciesResult is DataHolder.Success) {
            _currencies.value = DataHolder.Success(currenciesListMapper.map(currenciesResult.data))
        }
    }, error = {
        _currencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })
}