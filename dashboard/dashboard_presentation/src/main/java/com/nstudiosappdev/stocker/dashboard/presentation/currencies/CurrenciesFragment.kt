package com.nstudiosappdev.stocker.dashboard.presentation.currencies

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

        headerBankName.setOnClickListener {
            viewModel.orderCurrenciesByName()
        }

        headerBuyingPrice.setOnClickListener {
            viewModel.orderCurrenciesByBuyingPrices()
        }

        headerSellingPrice.setOnClickListener {
            viewModel.orderCurrenciesBySellingPrice()
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

    companion object {
        private const val BUNDLE_CURRENCY_TYPE = "bundle_currency_type"

        fun newInstance(currencyType: Int) = CurrenciesFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_CURRENCY_TYPE, currencyType)
            }
        }
    }
}