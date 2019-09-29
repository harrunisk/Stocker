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
    private val getSavedCurrenciesInteractor: Interactor.DeferredInteractor<GetSavedCurrenciesInteractor.Params, List<Currency>>,
    private val deleteCurrencyInteractor: Interactor.DeferredInteractor<DeleteCurrencyInteractor.Params, Boolean>,
    private val currenciesListMapper: DisplayItemListMapper<Currency>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

    private val selectedItems = ArrayList<Currency>()

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
            savedCurrenciesList = savedCurrenciesResult.data

            if(savedCurrenciesResult.data.isNullOrEmpty()){
                _filteredCurrencies.value = DataHolder.Fail(errorFactory.createBusinessError())
            } else if (!liveCurrenciesList.isNullOrEmpty()) filterElements()

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

            if(currenciesResult.data.isNullOrEmpty()) {
                _filteredCurrencies.value = DataHolder.Fail(errorFactory.createBusinessError())
            } else if (!savedCurrenciesList.isNullOrEmpty()) filterElements()
        }
    }, error = {
        _filteredCurrencies.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })

    private fun filterElements(){
        selectedItems.clear()

        savedCurrenciesList!!.forEach { savedCurrency ->
            liveCurrenciesList!!.forEach { liveCurrency ->
                if (savedCurrency.bankName == liveCurrency.bankName && savedCurrency.currencyType == liveCurrency.currencyType)
                    selectedItems.add(liveCurrency)
            }
        }

        _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems))
    }

    fun orderCurrenciesByName(){

        when(orderByBankNameFlag) {
            true -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedBy { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = false

            }
            false -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedByDescending { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = true
            }
        }
    }

    fun orderCurrenciesByBuyingPrices() {

        when(orderByBuyingPriceFlag) {
            true -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedBy { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = false
            }
            false -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedByDescending { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesBySellingPrice() {

        when(orderBySellingPriceFlag) {
            true -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedBy { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = false
            }
            false -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedByDescending { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesByDiff() {

        when(orderByDiffFlag) {
            true -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedBy {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                )
                )
                orderByDiffFlag = false
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
            }
            false -> {
                _filteredCurrencies.value = DataHolder.Success(currenciesListMapper.map(selectedItems!!.sortedByDescending {
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

    fun removeFromFavorites(
        bankName: String,
        currencyType: String
    ) = handleLaunch(execution = {
        _deleteCurrency.value = DataHolder.Loading
        val deleteCurrencyParams = DeleteCurrencyInteractor.Params(
            bankName,
            currencyType
        )
        val deleteCurrencyResult = deleteCurrencyInteractor.executeAsync(deleteCurrencyParams).await()
        if(deleteCurrencyResult is DataHolder.Success) _deleteCurrency.value = DataHolder.Success(deleteCurrencyResult.data)
    }, error = {
        _deleteCurrency.value = DataHolder.Fail(errorFactory.createErrorFromThrowable(it))
    })

    private fun clearAllFlags() {
        orderByBankNameFlag = false
        orderByBuyingPriceFlag = false
        orderBySellingPriceFlag = false
        orderByDiffFlag = false
    }
}