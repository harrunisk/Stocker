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

    private var items : List<Currency>? = null

    private var orderByBankNameFlag = false

    private var orderBySellingPriceFlag = false

    private var orderByBuyingPriceFlag = false

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
            items = currenciesResult.data
        }
    }, error = {
        _currencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })

    fun orderCurrenciesByName(){

        when(orderByBankNameFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.bankName }))
                orderByBankNameFlag = false
                orderByBuyingPriceFlag = true
                orderBySellingPriceFlag = true
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.bankName }))
                orderByBankNameFlag = true
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
            }
        }
    }

    fun orderCurrenciesByBuyingPrices() {

        when(orderByBuyingPriceFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.buyPrice }))
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = true
                orderByBankNameFlag = false
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.buyPrice }))
                orderByBuyingPriceFlag = true
                orderBySellingPriceFlag = false
                orderByBankNameFlag = true
            }
        }
    }

    fun orderCurrenciesBySellingPrice() {

        when(orderBySellingPriceFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.sellPrice }))
                orderBySellingPriceFlag = false
                orderByBuyingPriceFlag = true
                orderByBankNameFlag = false
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.sellPrice }))
                orderBySellingPriceFlag = true
                orderByBuyingPriceFlag = false
                orderByBankNameFlag = true
            }
        }
    }
}