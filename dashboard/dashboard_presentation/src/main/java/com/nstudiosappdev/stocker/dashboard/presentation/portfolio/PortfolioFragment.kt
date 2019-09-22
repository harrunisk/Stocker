package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import android.os.Bundle
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.presentation.R
import javax.inject.Inject

class PortfolioFragment : BaseViewModelFragment<PortfolioViewModel>() {

    private var currencyType: Int? = null

    @Inject
    lateinit var portfolioAdapter: RecyclerViewAdapter

    override fun getModelClass() = PortfolioViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_portfolio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyType = it.getInt(BUNDLE_CURRENCY_TYPE)
        }
    }

    companion object {
        private const val BUNDLE_CURRENCY_TYPE = "bundle_currency_type"

        fun newInstance(currencyType: Int) = PortfolioFragment().apply {
            arguments = Bundle().apply {
                putInt(BUNDLE_CURRENCY_TYPE, currencyType)
            }
        }
    }
}