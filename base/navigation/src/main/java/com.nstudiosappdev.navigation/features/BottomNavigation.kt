package com.nstudiosappdev.navigation.features

import androidx.fragment.app.Fragment
import com.nstudiosappdev.navigation.PACKAGE_NAME
import com.nstudiosappdev.navigation.loadFragmentOrReturnNull

object BottomNavigation : Feature<Fragment> {
    private const val BOTTOM_NAVIGATION =
        "$PACKAGE_NAME.dashboard.presentation.bottom.BottomNavigationFragment"

    override val dynamicStart: Fragment?
        get() = BOTTOM_NAVIGATION.loadFragmentOrReturnNull()
}
