package com.nstudiosappdev.stocker.ui

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.nstudiosappdev.core.presentation.TabProvider
import com.nstudiosappdev.core.presentation.base.BaseInjectionActivity
import com.nstudiosappdev.core.presentation.extensions.transact
import com.nstudiosappdev.navigation.features.BottomNavigation
import com.nstudiosappdev.navigation.features.Dashboard
import com.nstudiosappdev.navigation.navigation.DefaultNavigationController
import com.nstudiosappdev.navigation.navigation.NavigationController
import com.nstudiosappdev.stocker.R
import java.lang.ref.WeakReference

class MainActivity : BaseInjectionActivity(), TabProvider {

    private lateinit var tabs: TabLayout

    private lateinit var navigationController: NavigationController

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController = DefaultNavigationController(WeakReference(this))

        tabs = findViewById(R.id.tabs)

        navigationController.navigateToDashoard(R.id.fl_main)
        navigationController.navigateToBottomNavigation(R.id.fl_bottom_navigation)

    }

    override fun provideTabLayout(): TabLayout = tabs

}
