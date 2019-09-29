package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nstudiosappdev.core.coroutines.CoroutineManager
import com.nstudiosappdev.core.domain.Interactor
import com.nstudiosappdev.core.error.ErrorFactory
import com.nstudiosappdev.core.injection.modules.CoroutineManagerModule
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.extensions.adjustSensitivityGiveFloat
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItemListMapper
import com.nstudiosappdev.core.presentation.viewmodel.BaseViewModel
import com.nstudiosappdev.stocker.dashboard.domain.*
import javax.inject.Inject
import javax.inject.Named

class PortfolioViewModel @Inject constructor(
    @Named(CoroutineManagerModule.CM_VIEWMODEL) coroutineManager: CoroutineManager,
    private val getCurrenciesInteractor: Interactor.DeferredInteractor<GetCurrenciesInteractor.Params, List<Currency>>,
    private val getSavedCurrencyInteractor: Interactor.DeferredInteractor<GetSavedCurrencyInteractor.Params, Currency>,
    private val getSavedCurrenciesInteractor: Interactor.DeferredInteractor<GetSavedCurrenciesInteractor.Params, List<Currency>>,
    private val saveCurrencyInteractor: Interactor.DeferredInteractor<SaveCurrencyInteractor.Params, Boolean>,
    private val deleteCurrencyInteractor: Interactor.DeferredInteractor<DeleteCurrencyInteractor.Params, Boolean>,
    private val currenciesListMapper: DisplayItemListMapper<Currency>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

    private val selectedItems = ArrayList<Currency>()

    private val items: List<Currency>? = null

    private var savedCurrenciesList : List<Currency>? = null

    private var liveCurrenciesList : List<Currency>? = null

    private var orderByBankNameFlag = false

    private var orderBySellingPriceFlag = false

    private var orderByBuyingPriceFlag = false

    private var orderByDiffFlag = false

    private val _savedCurrencies = MutableLiveData<DataHolder<List<DisplayItem>>>()

    private val _liveCurrencies = MutableLiveData<DataHolder<List<DisplayItem>>>()

    private val _filteredCurrencies = MutableLiveData<DataHolder<List<DisplayItem>>>()

    private val _deleteCurrency = MutableLiveData<DataHolder<Boolean>>()


    val savedCurrencies: LiveData<DataHolder<List<DisplayItem>>>
        get() = _savedCurrencies

    val liveCurrencies: LiveData<DataHolder<List<DisplayItem>>>
        get() = _liveCurrencies

    val filteredCurrencies: LiveData<DataHolder<List<DisplayItem>>>
        get() = _filteredCurrencies

    val deleteCurrency: LiveData<DataHolder<Boolean>>
        get() = _deleteCurrency

    fun fetchSavedCurrencies(
        currencyType: String
    ) = handleLaunch(execution = {
        _savedCurrencies.value = DataHolder.Loading

        val savedCurrenciesParams = GetSavedCurrenciesInteractor.Params(
            currencyType = currencyType
        )

        val savedCurrenciesResult = getSavedCurrenciesInteractor.executeAsync(savedCurrenciesParams).await()
        if(savedCurrenciesResult is DataHolder.Success) {
//            _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(savedCurrenciesResult.data))
            savedCurrenciesList = savedCurrenciesResult.data
            if(!liveCurrenciesList.isNullOrEmpty()) filterElements()

        }
    }, error = {
        _filteredCurrencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })

    fun fetchLiveCurrencies(
        currencyType: String
    ) = handleLaunch(execution = {
        _liveCurrencies.value = DataHolder.Loading
        val currenciesParams = GetCurrenciesInteractor.Params(
            currencyType = currencyType
        )

        val currenciesResult = getCurrenciesInteractor.executeAsync(currenciesParams).await()
        if(currenciesResult is DataHolder.Success) {
            liveCurrenciesList = currenciesResult.data

            if(!savedCurrenciesList.isNullOrEmpty()) filterElements()
        }
    }, error = {
        _filteredCurrencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })

    private fun filterElements(){
        savedCurrenciesList!!.forEach { savedCurrency ->
            liveCurrenciesList!!.forEach { liveCurrency ->
                if (savedCurrency.bankName == liveCurrency.bankName && savedCurrency.currencyType == liveCurrency.currencyType)
                    selectedItems.add(liveCurrency)
            }
        }

        _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems))
        selectedItems.clear()
    }

    fun orderCurrenciesByName(){

        when(orderByBankNameFlag) {
            true -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = false

            }
            false -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = true
            }
        }
    }

    fun orderCurrenciesByBuyingPrices() {

        when(orderByBuyingPriceFlag) {
            true -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = false
            }
            false -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesBySellingPrice() {

        when(orderBySellingPriceFlag) {
            true -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = false
            }
            false -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesByDiff() {

        when(orderByDiffFlag) {
            true -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                )
                )
                orderByDiffFlag = false
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
            }
            false -> {
                _savedCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                )
                )
                orderByDiffFlag = true
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
            }
        }
    }

    private fun clearAllFlags() {
        orderByBankNameFlag = false
        orderByBuyingPriceFlag = false
        orderBySellingPriceFlag = false
        orderByDiffFlag = false
    }
}