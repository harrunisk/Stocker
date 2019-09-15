package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.dashboard.presentation.DashboardActivityModule
import com.nstudiosappdev.stocker.ui.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [DashboardActivityModule::class]
)
abstract class ActivityModule {
    @ContributesAndroidInjector
    @ActivityScope
    abstract fun contributeSplashActivityInjector(): SplashActivity
}
