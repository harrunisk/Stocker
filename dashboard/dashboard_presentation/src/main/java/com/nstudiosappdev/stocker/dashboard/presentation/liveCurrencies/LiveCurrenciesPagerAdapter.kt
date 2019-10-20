package com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardPresentationConstants
import java.lang.IllegalArgumentException

class LiveCurrenciesPagerAdapter(private val titles: MutableList<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val _fragments = SparseArray<Fragment>()

    private val fragments: SparseArray<Fragment>
        get() = _fragments

    override fun getItem(position: Int): Fragment {
        return when (position) {
            DashboardPresentationConstants.TYPES.USD -> LiveCurrenciesFragment.newInstance(
                DashboardPresentationConstants.TYPES_STRING.USD)
            DashboardPresentationConstants.TYPES.EURO -> LiveCurrenciesFragment.newInstance(
                DashboardPresentationConstants.TYPES_STRING.EURO)
            DashboardPresentationConstants.TYPES.GOLD -> LiveCurrenciesFragment.newInstance(
                DashboardPresentationConstants.TYPES_STRING.GOLD)
            else -> throw IllegalArgumentException("Unknown position!")
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        fragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}
