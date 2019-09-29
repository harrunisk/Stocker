package com.nstudiosappdev.stocker.dashboard.presentation

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.nstudiosappdev.core.presentation.TabProvider
import com.nstudiosappdev.core.presentation.base.BaseInjectionActivity
import com.nstudiosappdev.navigation.navigation.DefaultNavigationController
import com.nstudiosappdev.navigation.navigation.NavigationController
import com.nstudiosappdev.stocker.presentation.R
import java.lang.ref.WeakReference

class DashboardActivity : BaseInjectionActivity(), TabProvider {

    private lateinit var tabs: TabLayout

    private lateinit var navigationController: NavigationController

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController = DefaultNavigationController(WeakReference(this))

        tabs = findViewById(R.id.tabs)
        navigationController.navigateToCurrencies(R.id.fl_main)
        navigationController.navigateToBottomNavigation(R.id.fl_bottom_navigation)

    }

    override fun provideTabLayout(): TabLayout = tabs

    override fun onBackPressed() {
        // no-op
    }
}
