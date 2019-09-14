package com.nstudiosappdev.stocker.ui

import android.os.Bundle
import android.os.Handler
import com.nstudiosappdev.core.presentation.base.BaseInjectionActivity
import com.nstudiosappdev.navigation.navigation.DefaultNavigationController
import com.nstudiosappdev.navigation.navigation.NavigationController
import com.nstudiosappdev.stocker.R
import java.lang.ref.WeakReference

class SplashActivity : BaseInjectionActivity() {

    private lateinit var navigationController: NavigationController

    override fun getLayoutRes(): Int = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.navigationController = DefaultNavigationController(WeakReference(this))

        Handler().postDelayed({

            navigationController.navigateToMain()

        }, SPLASH_TIME_OUT)
    }

    companion object{
        const val SPLASH_TIME_OUT = 1000L
    }
}