package com.nstudiosappdev.stocker.ui

import android.os.Bundle
import android.widget.TableLayout
import com.nstudiosappdev.core.presentation.base.BaseActivity
import com.nstudiosappdev.core.presentation.navigation.UiNavigation
import com.nstudiosappdev.navigation.features.Currencies
import com.nstudiosappdev.stocker.R

class MainActivity : BaseActivity() {

    private lateinit var tabs: TableLayout

    override fun getLayoutRes() = R.layout.activity_main

    override val uiNavigation = UiNavigation.ROOT

    override val toolbarRes = R.id.default_toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tabs = findViewById(R.id.tabs)

        Currencies.dynamicStart?.let {
            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fl_main, it)
                    .commit()
            }
        }
    }
}
