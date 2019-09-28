package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import android.os.Bundle
import android.view.View
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
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

        viewModel.filteredCurrencies.observeApi(this) {
            when (it) {
                is DataHolder.Success -> {
                    portfolioAdapter.updateDiffItemsOnly(it.data)
                    portfolioAdapter.itemClickListener
                }
            }
        }

        pullToRefreshPortfolio.setOnRefreshListener {
            viewModel.fetchLiveCurrencies(currencyType!!)
            viewModel.fetchLiveCurrencies(currencyType!!)
            pullToRefreshPortfolio.isRefreshing = false
        }
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