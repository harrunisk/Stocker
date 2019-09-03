package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import android.os.Bundle
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

class CurrenciesFragment : BaseViewModelFragment<CurrenciesViewModel>(){

    @Inject
    lateinit var currenciesAdapter: RecyclerViewAdapter

    override fun getModelClass() = CurrenciesViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }


    override fun initView() {
        super.initView()
        initListeners()
    }

    private fun initListeners() {
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
        viewModel.fetchCurrencies()
    }

    companion object {
        fun newInstance() =
            CurrenciesFragment()
    }
}