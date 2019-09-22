package com.nstudiosappdev.navigation.features

import androidx.fragment.app.Fragment
import com.nstudiosappdev.navigation.PACKAGE_NAME
import com.nstudiosappdev.navigation.loadFragmentOrReturnNull

object Portfolio : Feature<Fragment> {
    private const val PORTFOLIO = "$PACKAGE_NAME.dashboard.presentation.portfolio.PortfolioMainFragment"

    override val dynamicStart: Fragment?
        get() = PORTFOLIO.loadFragmentOrReturnNull()
}