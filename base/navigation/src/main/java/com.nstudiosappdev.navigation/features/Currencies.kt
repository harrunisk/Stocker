package com.nstudiosappdev.navigation.features

import androidx.fragment.app.Fragment
import com.nstudiosappdev.navigation.PACKAGE_NAME
import com.nstudiosappdev.navigation.loadFragmentOrReturnNull

object Currencies : Feature<Fragment> {
    private const val CURRENCIES = "$PACKAGE_NAME.dashboard.presentation.liveCurrencies.LiveCurrenciesMainFragment"

    override val dynamicStart: Fragment?
        get() = CURRENCIES.loadFragmentOrReturnNull()
}
