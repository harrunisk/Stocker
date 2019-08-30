package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.presentation.base.BaseFragment
import com.nstudiosappdev.stocker.presentation.R

class CurrenciesFragment : BaseFragment(){
    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    companion object {
        fun newInstance() = CurrenciesFragment()
    }
}