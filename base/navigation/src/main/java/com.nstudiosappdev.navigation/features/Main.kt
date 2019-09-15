package com.nstudiosappdev.navigation.features

import android.content.Intent
import com.nstudiosappdev.navigation.PACKAGE_NAME
import com.nstudiosappdev.navigation.loadIntentOrReturnNull

object Main : Feature<Intent> {
    private const val MAIN = "$PACKAGE_NAME.dashboard.presentation.DashboardActivity"

    override val dynamicStart: Intent?
        get() = MAIN.loadIntentOrReturnNull()
}