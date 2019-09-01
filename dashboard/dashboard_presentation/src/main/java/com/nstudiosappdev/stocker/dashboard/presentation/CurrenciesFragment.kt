package com.nstudiosappdev.stocker.dashboard.presentation

import android.os.Bundle
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.stocker.presentation.R

class CurrenciesFragment : BaseViewModelFragment<CurrenciesViewModel>(){

    override fun getModelClass() = CurrenciesViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.currencies.observeApi(this) {

        }
        viewModel.fetchCurrencies()
    }

    companion object {
        fun newInstance() = CurrenciesFragment()
    }
}