package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.createCustomAlertDialog
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesViewEntity
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_portfolio.*
import javax.inject.Inject

class PortfolioFragment : BaseViewModelFragment<PortfolioViewModel>() {

    private var currencyType: String? = null

    @Inject
    lateinit var portfolioAdapter: RecyclerViewAdapter

    override fun getModelClass() = PortfolioViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_portfolio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyType = it.getString(BUNDLE_CURRENCY_TYPE)
            viewModel.fetchSavedCurrencies(currencyType!!)
            viewModel.fetchLiveCurrencies(currencyType!!)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        portfolioRecyclerView.setup(
            adapter = portfolioAdapter,
            context = context!!
        )

    }

    override fun initView() {
        super.initView()
        initListeners()
    }

    private fun initObservers() {

        viewModel.filteredCurrencies.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    portfolioAdapter.updateDiffItemsOnly(it.data)
                    portfolioAdapter.itemClickListener
                }
            }
        }

        viewModel.deleteCurrency.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    viewModel.fetchSavedCurrencies(currencyType!!)
                    viewModel.fetchLiveCurrencies(currencyType!!)
                }
            }
        }
    }

    private fun initListeners() {

        pullToRefreshPortfolio.setOnRefreshListener {
            viewModel.fetchLiveCurrencies(currencyType!!)
            viewModel.fetchSavedCurrencies(currencyType!!)
            pullToRefreshPortfolio.isRefreshing = false
        }

        portfolioAdapter.itemClickListener = this.itemClickListener
        portfolioAdapter.itemLongClickListener = this.itemClickListener

        headerBankNameLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByName()
            if (bankNameSortByDecreasingSign.currentTextColor != Color.GREEN) {
                clearAllColor()
                bankNameSortByDecreasingSign.setTextColor(Color.GREEN)
            } else {
                clearAllColor()
                bankNameSortByIncreasingSign.setTextColor(Color.GREEN)
            }
        }

        headerBuyingPriceLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByBuyingPrices()
            if (buyingPriceSortByIncreasingSign.currentTextColor != Color.GREEN) {
                clearAllColor()
                buyingPriceSortByIncreasingSign.setTextColor(Color.GREEN)
            } else {
                clearAllColor()
                buyingPriceSortByDecreasingSign.setTextColor(Color.GREEN)
            }
        }

        headerSellingPriceLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesBySellingPrice()
            if (sellingPriceSortByIncreasingSign.currentTextColor != Color.GREEN) {
                clearAllColor()
                sellingPriceSortByIncreasingSign.setTextColor(Color.GREEN)
            } else {
                clearAllColor()
                sellingPriceSortByDecreasingSign.setTextColor(Color.GREEN)
            }
        }

        headerDiffLinearLayout.setOnClickListener {
            viewModel.orderCurrenciesByDiff()
            if (diffSortByIncreasingSign.currentTextColor != Color.GREEN) {
                clearAllColor()
                diffSortByIncreasingSign.setTextColor(Color.GREEN)
            } else {
                clearAllColor()
                diffSortByDecreasingSign.setTextColor(Color.GREEN)
            }
        }
    }

    private val itemClickListener = { v: View, item: DisplayItem ->
        val currenciesViewEntity = item as CurrenciesViewEntity

        v.context.createCustomAlertDialog(
            message = currenciesViewEntity.bankName + " " + currenciesViewEntity.currencyType?.toUpperCase() + " " + v.context.getString(R.string.message_remove),
            title = v.context.getString(R.string.title),
            positiveButtonText = v.context.getString(R.string.remove),
            positiveButtonAction = {
                viewModel.removeFromFavorites(
                    currenciesViewEntity.bankName!!,
                    currenciesViewEntity.currencyType!!
                )
            },
            negativeButtonText = v.context.getString(R.string.cancel),
            imageView = null
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

        fun newInstance(currencyType: String) = PortfolioFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_CURRENCY_TYPE, currencyType)
            }
        }
    }
}