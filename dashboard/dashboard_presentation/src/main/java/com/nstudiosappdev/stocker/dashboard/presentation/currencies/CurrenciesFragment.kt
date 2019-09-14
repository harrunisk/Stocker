package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import android.graphics.Color
import android.os.Bundle
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_currencies.*
import kotlinx.android.synthetic.main.item_currency.*
import javax.inject.Inject

class CurrenciesFragment : BaseViewModelFragment<CurrenciesViewModel>(){

    private var currencyType: Int? = null

    @Inject
    lateinit var currenciesAdapter: RecyclerViewAdapter

    override fun getModelClass() = CurrenciesViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyType = it.getInt(BUNDLE_CURRENCY_TYPE)
            viewModel.fetchCurrencies(currencyType!!)
        }
    }


    override fun initView() {
        super.initView()
        initListeners()
    }

    private fun initListeners() {

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

    private fun initObservers() {
        viewModel.currencies.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    currenciesAdapter.updateAllItems(it.data)
                    currenciesRecyclerView.setup(
                        adapter = currenciesAdapter,
                        context = activity!!
                    )
                }

            }
        }
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

        fun newInstance(currencyType: Int) = CurrenciesFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_CURRENCY_TYPE, currencyType)
            }
        }
    }
}