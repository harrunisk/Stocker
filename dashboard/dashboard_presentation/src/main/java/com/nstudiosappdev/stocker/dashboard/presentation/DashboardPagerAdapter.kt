package com.nstudiosappdev.stocker.dashboard.presentation

import android.util.Log
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesFragment
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesPresentationConstants
import java.lang.IllegalArgumentException

class DashboardPagerAdapter(private val titles: MutableList<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val _fragments = SparseArray<Fragment>()

    val fragments: SparseArray<Fragment>
        get() = _fragments

    override fun getItem(position: Int): Fragment {
        Log.e("counter", "counter")
        return when (position) {
            CurrenciesPresentationConstants.TYPES.USD -> CurrenciesFragment.newInstance()
            CurrenciesPresentationConstants.TYPES.EURO -> CurrenciesFragment.newInstance()
            CurrenciesPresentationConstants.TYPES.GOLD -> CurrenciesFragment.newInstance()
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