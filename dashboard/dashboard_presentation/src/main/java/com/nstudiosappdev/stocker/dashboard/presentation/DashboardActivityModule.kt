package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesFragmentModule
import com.nstudiosappdev.stocker.dashboard.presentation.portfolio.PortfolioFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [CurrenciesFragmentModule::class,
        PortfolioFragmentModule::class]
    )
    abstract fun provideDashboardActivity(): DashboardActivity
}