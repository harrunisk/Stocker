package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesViewModel
import com.nstudiosappdev.stocker.presentation.R

class PortfolioFragment : BaseViewModelFragment<CurrenciesViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_portfolio

    override fun getModelClass(): Class<CurrenciesViewModel> = CurrenciesViewModel::class.java

}