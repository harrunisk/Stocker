package com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.enums.DialogType
import com.nstudiosappdev.core.presentation.extensions.createCustomAlertDialog
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.extensions.toColor
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.dashboard.domain.Currency
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesViewEntity
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

class LiveCurrenciesFragment : BaseViewModelFragment<LiveCurrenciesViewModel>() {

    private var currencyType: String? = null


    @Inject
    lateinit var liveCurrenciesAdapter: RecyclerViewAdapter

    override fun getModelClass() = LiveCurrenciesViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyType = it.getString(BUNDLE_CURRENCY_TYPE)
            viewModel.fetchCurrencies(currencyType!!)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
        pullToRefreshCurrencies.isRefreshing = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currenciesRecyclerView.setup(
            adapter = liveCurrenciesAdapter,
            context = context!!
        )
    }

    override fun initView() {
        super.initView()
        initListeners()
    }

    private fun initListeners() {

        liveCurrenciesAdapter.itemClickListener = this.itemClickListener
        liveCurrenciesAdapter.itemLongClickListener = this.itemClickListener

        headerBankNameLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByName()
            if (bankNameSortByDecreasingSign.currentTextColor != ContextCompat.getColor(context!!, R.color.green_currency)) {
                clearAllColor()
                bankNameSortByDecreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            } else {
                clearAllColor()
                bankNameSortByIncreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            }
        }

        headerBuyingPriceLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByBuyingPrices()
            if (buyingPriceSortByIncreasingSign.currentTextColor != ContextCompat.getColor(context!!, R.color.green_currency)) {
                clearAllColor()
                buyingPriceSortByIncreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            } else {
                clearAllColor()
                buyingPriceSortByDecreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            }
        }

        headerSellingPriceLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesBySellingPrice()
            if (sellingPriceSortByIncreasingSign.currentTextColor != ContextCompat.getColor(context!!, R.color.green_currency)) {
                clearAllColor()
                sellingPriceSortByIncreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            } else {
                clearAllColor()
                sellingPriceSortByDecreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            }
        }

        headerDiffLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByDiff()
            if (diffSortByIncreasingSign.currentTextColor != ContextCompat.getColor(context!!, R.color.green_currency)) {
                clearAllColor()
                diffSortByIncreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            } else {
                clearAllColor()
                diffSortByDecreasingSign.setTextColor(ContextCompat.getColor(context!!, R.color.green_currency))
            }
        }
    }

    private fun initObservers() {
        viewModel.liveCurrencies.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    liveCurrenciesLinearLayout.visibility = View.VISIBLE
                    liveCurrenciesAdapter.updateDiffItemsOnly(it.data)
                    liveCurrenciesAdapter.itemClickListener
                    pullToRefreshCurrencies.isRefreshing = false
                }
                is DataHolder.Fail -> {
                    liveCurrenciesLinearLayout.visibility = View.GONE
                    notFoundLiveCurrenciesAnimation.visibility = View.VISIBLE
                    pullToRefreshCurrencies.isRefreshing = false
                }
            }
        }

        viewModel.saveFavorites.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    createInfoAlertDialogWithMessage(activity!!.getString(R.string.text_adding_favorites_is_successfull))
                }
                is DataHolder.Fail -> {
                    createInfoAlertDialogWithMessage(activity!!.getString(R.string.text_already_have))
                }
            }
        }

        pullToRefreshCurrencies.setOnRefreshListener {
            viewModel.fetchCurrencies(currencyType!!)
            pullToRefreshCurrencies.isRefreshing = false
        }
    }

    private val itemClickListener = { v: View, item: DisplayItem ->
        val currenciesViewEntity = item as CurrenciesViewEntity

        v.context.createCustomAlertDialog(
            message = currenciesViewEntity.bankName + " " + currenciesViewEntity.currencyType?.toUpperCase() + " " + v.context.getString(R.string.text_add_currency),
            title = v.context.getString(R.string.text_info),
            positiveButtonText = v.context.getString(R.string.text_add),
            positiveButtonAction = {
                viewModel.addToFavorites(
                    Currency(
                        bankName = currenciesViewEntity.bankName,
                        buyPrice  = currenciesViewEntity.buyPrice,
                        buyStatus = currenciesViewEntity.buyStatus,
                        sellPrice = currenciesViewEntity.sellPrice,
                        sellStatus = currenciesViewEntity.sellStatus,
                        currencyType = currenciesViewEntity.currencyType
                    ))
            },
            negativeButtonText = v.context.getString(R.string.text_cancel),
            imageView = null
        ).show()
    }

    private fun createInfoAlertDialogWithMessage(message: String) {
        context!!.createCustomAlertDialog(
            message = message,
            title = activity!!.getString(R.string.text_info),
            positiveButtonText = activity!!.getString(R.string.text_ok),
            imageView = null,
            alertType = DialogType.INFO
        ).show()
    }

    private fun clearAllColor() {
        bankNameSortByIncreasingSign.setTextColor(Color.GRAY)
        bankNameSortByDecreasingSign.setTextColor(Color.GRAY)
        buyingPriceSortByIncreasingSign.setTextColor(Color.GRAY)
        buyingPriceSortByDecreasingSign.setTextColor(Color.GRAY)
        sellingPriceSortByIncreasingSign.setTextColor(Color.GRAY)
        sellingPriceSortByDecreasingSign.setTextColor(Color.GRAY)
        diffSortByIncreasingSign.setTextColor(Color.GRAY)
        diffSortByDecreasingSign.setTextColor(Color.GRAY)
    }

    companion object {
        private const val BUNDLE_CURRENCY_TYPE = "bundle_currency_type"

        fun newInstance(currencyType: String) = LiveCurrenciesFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_CURRENCY_TYPE, currencyType)
            }
        }
    }
}