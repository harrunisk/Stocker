package com.nstudiosappdev.navigation.navigation

import androidx.fragment.app.FragmentActivity
import com.nstudiosappdev.navigation.features.BottomNavigation
import com.nstudiosappdev.navigation.features.Currencies
import com.nstudiosappdev.navigation.features.Dashboard
import com.nstudiosappdev.navigation.features.Main
import java.lang.ref.WeakReference

class DefaultNavigationController constructor(
    override val activity: WeakReference<FragmentActivity>
): NavigationController  {

    override fun navigateToMain() = start(Main.dynamicStart)

    override fun navigateToCurrencies(containerIdRes: Int) =
        start(Dashboard.dynamicStart,
            containerIdRes, transaction = {
                add(containerIdRes, Dashboard.dynamicStart!!).addToBackStack(null)
            })

    override fun navigateToBottomNavigation(containerIdRes: Int) =
        start(BottomNavigation.dynamicStart,
            containerIdRes, transaction = {
                add(containerIdRes, BottomNavigation.dynamicStart!!).addToBackStack(null)
            })
}