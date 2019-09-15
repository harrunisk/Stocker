package com.nstudiosappdev.navigation.features

import androidx.fragment.app.Fragment
import com.nstudiosappdev.navigation.PACKAGE_NAME
import com.nstudiosappdev.navigation.loadFragmentOrReturnNull

object Dashboard : Feature<Fragment> {
    private const val DASHBOARD = "$PACKAGE_NAME.dashboard.presentation.currencies.CurrenciesMainFragment"

    override val dynamicStart: Fragment?
        get() = DASHBOARD.loadFragmentOrReturnNull()
}

