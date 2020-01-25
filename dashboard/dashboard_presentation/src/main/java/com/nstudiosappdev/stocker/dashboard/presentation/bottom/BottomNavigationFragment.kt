package com.nstudiosappdev.stocker.dashboard.presentation.bottom

import android.os.Bundle
import android.view.View
import com.nstudiosappdev.core.presentation.base.BaseFragment
import com.nstudiosappdev.navigation.navigation.DefaultNavigationController
import com.nstudiosappdev.stocker.presentation.R
import java.lang.ref.WeakReference
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

class BottomNavigationFragment : BaseFragment() {

    lateinit var navigationController: DefaultNavigationController

    override fun getLayoutRes() = R.layout.fragment_bottom_navigation

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationCurrencies -> {
                    navigationController.navigateToCurrencies(R.id.fl_main)
                    true
                }
                R.id.navigationPortfolio -> {
                    navigationController.navigateToPortfolio(R.id.fl_main)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController = DefaultNavigationController(WeakReference(activity!!))
    }
}
