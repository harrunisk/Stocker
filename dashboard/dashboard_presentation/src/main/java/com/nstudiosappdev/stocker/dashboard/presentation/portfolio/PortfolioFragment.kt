package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.createCustomAlertDialog
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.DisplayItem
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesViewEntity
import com.nstudiosappdev.stocker.presentation.R
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_portfolio.*

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
                    favoritesLinearLayout.visibility = View.VISIBLE
                    notFoundAnimation.visibility = View.GONE
                    portfolioAdapter.updateDiffItemsOnly(it.data)
                    portfolioAdapter.itemClickListener
                }
                is DataHolder.Fail -> {
                    favoritesLinearLayout.visibility = View.GONE
                    notFoundAnimation.visibility = View.VISIBLE
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

    private val itemClickListener = { v: View, item: DisplayItem ->
        val currenciesViewEntity = item as CurrenciesViewEntity

        v.context.createCustomAlertDialog(
            message = v.context.getString(R.string.text_remove_currency),
            title = v.context.getString(R.string.text_info),
            positiveButtonText = v.context.getString(R.string.text_remove),
            positiveButtonAction = {
                viewModel.removeFromFavorites(
                    currenciesViewEntity.bankName!!,
                    currenciesViewEntity.currencyType!!
                )
            },
            negativeButtonText = v.context.getString(R.string.text_cancel)
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
