package com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies

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
import com.nstudiosappdev.stocker.dashboard.domain.SaveCurrencyInteractor
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardPresentationConstants
import com.nstudiosappdev.stocker.dashboard.presentation.OrderingStyle
import javax.inject.Inject
import javax.inject.Named

class LiveCurrenciesViewModel @Inject constructor(
    @Named(CoroutineManagerModule.CM_VIEWMODEL) coroutineManager: CoroutineManager,
    private val getCurrenciesInteractor: Interactor.DeferredInteractor<GetCurrenciesInteractor.Params, List<Currency>>,
    private val currenciesListMapper: DisplayItemListMapper<Currency>,
    private val saveCurrencyInteractor: Interactor.DeferredInteractor<SaveCurrencyInteractor.Params, Boolean>,
    private val errorFactory: ErrorFactory
) : BaseViewModel(coroutineManager) {

    private var items : List<Currency>? = null

    private var orderByBankNameFlag = false

    private var orderBySellingPriceFlag = false

    private var orderByBuyingPriceFlag = false

    private var orderByDiffFlag = false

    private val _liveCurrencies = MutableLiveData<DataHolder<List<DisplayItem>>>()

    private val _saveFavorites = MutableLiveData<DataHolder<Boolean>>()

    private var orderingStyle : Int = 0


    val liveCurrencies: LiveData<DataHolder<List<DisplayItem>>>
        get() = _liveCurrencies

    val saveFavorites: LiveData<DataHolder<Boolean>>
        get() = _saveFavorites


    fun fetchCurrencies(
        currencyType: String
    ) = handleLaunch(execution = {
        _liveCurrencies.value = DataHolder.Loading
        val currenciesParams = GetCurrenciesInteractor.Params(
            currencyType = currencyType
        )

        val currenciesResult = getCurrenciesInteractor.executeAsync(currenciesParams).await()
        if(currenciesResult is DataHolder.Success && !currenciesResult.data.isNullOrEmpty()) {
            _liveCurrencies.value = when(orderingStyle) {
                OrderingStyle.BY_NAME.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedBy { it.bankName }))
                OrderingStyle.BY_NAME_DESC.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedByDescending { it.bankName }))
                OrderingStyle.BY_BUYING_PRICE.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedBy { it.buyPrice }))
                OrderingStyle.BY_BUYING_PRICE_DESC.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedByDescending { it.buyPrice }))
                OrderingStyle.BY_SELLING_PRICE.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedBy { it.sellPrice }))
                OrderingStyle.BY_SELLING_PRICE_DESC.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedByDescending { it.sellPrice }))
                OrderingStyle.BY_DIFF.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedBy {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }))
                OrderingStyle.BY_DIFF_DESC.code -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data.sortedByDescending {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }))
                else -> DataHolder.Success(currenciesListMapper.map(currenciesResult.data))
            }
            items = currenciesResult.data
        } else {
            _liveCurrencies.value = DataHolder.Fail(errorFactory.createConnectionError())
        }
    }, error = {
        _liveCurrencies.value = DataHolder.Fail(errorFactory.createConnectionError())
    })

    fun orderCurrenciesByName(){

        when(orderByBankNameFlag) {
            true -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.bankName }))
                clearAllFlags()
                orderingStyle = OrderingStyle.BY_NAME.code
            }
            false -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.bankName }))
                clearAllFlags()
                orderByBankNameFlag = true
                orderingStyle = OrderingStyle.BY_NAME_DESC.code
            }
        }
    }

    fun orderCurrenciesByBuyingPrices() {

        when(orderByBuyingPriceFlag) {
            true -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.buyPrice }))
                clearAllFlags()
                orderingStyle = OrderingStyle.BY_BUYING_PRICE.code
            }
            false -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.buyPrice }))
                clearAllFlags()
                orderByBuyingPriceFlag = true
                orderingStyle = OrderingStyle.BY_BUYING_PRICE_DESC.code
            }
        }
    }

    fun orderCurrenciesBySellingPrice() {

        when(orderBySellingPriceFlag) {
            true -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy { it.sellPrice }))
                clearAllFlags()
                orderingStyle = OrderingStyle.BY_SELLING_PRICE.code
            }
            false -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending { it.sellPrice }))
                clearAllFlags()
                orderBySellingPriceFlag = true
                orderingStyle = OrderingStyle.BY_SELLING_PRICE_DESC.code
            }
        }
    }

    fun orderCurrenciesByDiff() {

        when(orderByDiffFlag) {
            true -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedBy {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                    )
                )
                orderByDiffFlag = false
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
                orderingStyle = OrderingStyle.BY_DIFF.code
            }
            false -> {
                _liveCurrencies.value = DataHolder.Success(currenciesListMapper.map(items!!.sortedByDescending {
                    it.sellPrice!!.adjustSensitivityGiveFloat(3) - it.buyPrice!!.adjustSensitivityGiveFloat(3) }
                )
                )
                orderByDiffFlag = true
                orderByBuyingPriceFlag = false
                orderBySellingPriceFlag = false
                orderByBankNameFlag = false
                orderingStyle = OrderingStyle.BY_DIFF_DESC.code
            }
        }
    }

    fun addToFavorites(
        currency: Currency
    ) = handleLaunch (execution = {
        _saveFavorites.value = DataHolder.Loading
        val saveCurrencyParams = SaveCurrencyInteractor.Params(
            currency
        )

        val saveCurrencyResult = saveCurrencyInteractor.executeAsync(saveCurrencyParams).await()
        if (saveCurrencyResult is DataHolder.Success) _saveFavorites.value = DataHolder.Success(saveCurrencyResult.data)
    }, error = {
        _saveFavorites.value = DataHolder.Fail(errorFactory.createConnectionError())
    })

    private fun clearAllFlags() {
        orderByBankNameFlag = false
        orderByBuyingPriceFlag = false
        orderBySellingPriceFlag = false
        orderByDiffFlag = false
    }

}