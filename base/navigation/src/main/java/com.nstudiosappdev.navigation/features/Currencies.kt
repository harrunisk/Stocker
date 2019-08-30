package com.nstudiosappdev.navigation.features

import androidx.fragment.app.Fragment
import com.nstudiosappdev.navigation.loadFragmentOrReturnNull
import com.nstudiosappdev.stocker.navigation.BuildConfig.PACKAGE_NAME

object Currencies : Feature<Fragment> {
    private const val CURRENCIES = "$PACKAGE_NAME.dashboard.presentation.CurrenciesFragment"

    override val dynamicStart: Fragment?
        get() = CURRENCIES.loadFragmentOrReturnNull()
}

