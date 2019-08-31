package com.nstudiosappdev.stocker.ui

import android.os.Bundle
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayout
import com.nstudiosappdev.core.presentation.TabProvider
import com.nstudiosappdev.core.presentation.base.BaseActivity
import com.nstudiosappdev.core.presentation.extensions.transact
import com.nstudiosappdev.core.presentation.navigation.UiNavigation
import com.nstudiosappdev.navigation.features.Currencies
import com.nstudiosappdev.stocker.R

class MainActivity : BaseActivity(), TabProvider {

    private lateinit var tabs: TabLayout

    override fun getLayoutRes() = R.layout.activity_main

    override val uiNavigation = UiNavigation.ROOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabs = findViewById(R.id.tabs)

        Currencies.dynamicStart?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.transact {
                    replace(R.id.fl_main, it)
                }
            }
        }
    }

    override fun provideTabLayout(): TabLayout = tabs

}
