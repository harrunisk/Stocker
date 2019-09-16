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

    override fun navigateToCurrencies(containerIdRes: Int) =
        start(Currencies.dynamicStart,
            containerIdRes, transaction = {
                replace(containerIdRes, Currencies.dynamicStart!!).addToBackStack(null)
            })

    override fun navigateToBottomNavigation(containerIdRes: Int) =
        start(BottomNavigation.dynamicStart,
            containerIdRes, transaction = {
                replace(containerIdRes, BottomNavigation.dynamicStart!!).addToBackStack(null)
            })

    override fun navigateToPortfolio(containerIdRes: Int) =
        start(Portfolio.dynamicStart,
            containerIdRes, transaction = {
                replace(containerIdRes, Portfolio.dynamicStart!!).addToBackStack(null)
            })
}