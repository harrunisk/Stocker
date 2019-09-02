package com.nstudiosappdev.stocker.dashboard.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.nstudiosappdev.core.model.DataHolder
import com.nstudiosappdev.core.presentation.TabProvider
import com.nstudiosappdev.core.presentation.base.ActionModeListener
import com.nstudiosappdev.core.presentation.base.BaseViewModelFragment
import com.nstudiosappdev.core.presentation.extensions.setup
import com.nstudiosappdev.core.presentation.livedata.observeApi
import com.nstudiosappdev.core.presentation.recyclerview.RecyclerViewAdapter
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_currencies.*
import javax.inject.Inject

class CurrenciesFragment : BaseViewModelFragment<CurrenciesViewModel>(){

    private lateinit var pagerAdapter: CurrenciesPagerAdapter

    private var lastItem: Int = 0

    @Inject
    lateinit var currenciesAdapter: RecyclerViewAdapter

    override fun getModelClass() = CurrenciesViewModel::class.java

    override fun getLayoutRes(): Int = R.layout.fragment_currencies

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = CurrenciesPagerAdapter(
            resources.getStringArray(R.array.main_items).toMutableList(),
            childFragmentManager
        )
        viewPagerCurrencies.apply {
            adapter = pagerAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                    // no-op
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    // no-op
                }

                @SuppressLint("MissingPermission")
                override fun onPageSelected(position: Int) {
                    (pagerAdapter.fragments[lastItem] as? ActionModeListener)?.stopActionMode()
                    lastItem = position
                }

            })
            offscreenPageLimit = 3

            (activity as TabProvider).provideTabLayout().setupWithViewPager(viewPagerCurrencies)
            lastItem = viewPagerCurrencies.currentItem

        }

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
        fun newInstance() = CurrenciesFragment()
    }
}