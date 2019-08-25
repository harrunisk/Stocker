package com.nstudiosappdev.stocker.ui

import android.os.Bundle
import com.nstudiosappdev.core.presentation.base.BaseActivity
import com.nstudiosappdev.core.presentation.navigation.UiNavigation
import com.nstudiosappdev.stocker.R

class MainActivity : BaseActivity() {

    override fun getLayoutRes() = R.layout.activity_main

    override val uiNavigation = UiNavigation.ROOT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
