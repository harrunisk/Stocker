package com.nstudiosappdev.stocker.dashboard.presentation.currencies

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

    private var orderByDiffFlag = false

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
                clearAllFlags()
                orderByBankNameFlag = false

            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = true
            }
        }
    }

    fun orderCurrenciesByBuyingPrices() {

        when(orderByBuyingPriceFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = false
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesBySellingPrice() {

        when(orderBySellingPriceFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = false
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = true
            }
        }
    }

    fun orderCurrenciesByDiff() {

        when(orderByDiffFlag) {
            true -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                    )
                )
                orderByDiffFlag = false
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
            }
            false -> {
                _currencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending {
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