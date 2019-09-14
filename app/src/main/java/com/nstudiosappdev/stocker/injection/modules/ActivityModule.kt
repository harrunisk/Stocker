package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesFragmentModule
import com.nstudiosappdev.stocker.ui.MainActivity
import com.nstudiosappdev.stocker.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [CurrenciesFragmentModule::class]
)
abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeSplashActivityInjector(): SplashActivity

    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeMainActivityInjector(): MainActivity
}
