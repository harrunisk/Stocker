package com.nstudiosappdev.navigation.navigation

import androidx.fragment.app.FragmentActivity
import com.nstudiosappdev.navigation.features.BottomNavigation
import com.nstudiosappdev.navigation.features.Currencies
import com.nstudiosappdev.navigation.features.Main
import com.nstudiosappdev.navigation.features.Portfolio
import java.lang.ref.WeakReference

class DefaultNavigationController constructor(
    override val activity: WeakReference<FragmentActivity>
): NavigationController  {

    override fun navigateToMain() = start(Main.dynamicStart)

    override fun navigateToCurrencies(containerId: Int) =
        start(Currencies.dynamicStart,
            containerId, transaction = {
                replace(containerId, Currencies.dynamicStart!!).addToBackStack(null)
            })

    override fun navigateToBottomNavigation(containerId: Int) =
        start(BottomNavigation.dynamicStart,
            containerId, transaction = {
                replace(containerId, BottomNavigation.dynamicStart!!).addToBackStack(null)
            })

    override fun navigateToPortfolio(containerId: Int) =
        start(Portfolio.dynamicStart,
            containerId, transaction = {
                replace(containerId, Portfolio.dynamicStart!!).addToBackStack(null)
            })
}