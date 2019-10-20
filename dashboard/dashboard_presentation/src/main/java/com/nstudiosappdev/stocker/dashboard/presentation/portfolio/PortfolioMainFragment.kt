package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.nstudiosappdev.core.presentation.TabProvider
import com.nstudiosappdev.core.presentation.base.BaseFragment
import com.nstudiosappdev.stocker.presentation.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class PortfolioMainFragment : BaseFragment() {

    private var lastItem: Int = 0

    private lateinit var pagerAdapter: PortfolioPagerAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_dashboard

    override val toolbarId = R.id.default_toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter =
            PortfolioPagerAdapter(
                resources.getStringArray(R.array.main_items).toMutableList(),
                childFragmentManager
            )

        viewPagerDashboard.apply {
            adapter = pagerAdapter
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                    // no-op
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    // no-op
                }

                override fun onPageSelected(position: Int) {
                    lastItem = position
                }
            })
            offscreenPageLimit = 3

            (activity as TabProvider).provideTabLayout().setupWithViewPager(viewPagerDashboard)
            lastItem = viewPagerDashboard.currentItem
        }
    }
}
