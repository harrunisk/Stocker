package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [CurrenciesFragmentModule::class]
    )
    abstract fun provideDashboardActivity(): DashboardActivity
}