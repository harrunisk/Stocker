package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.injection.scope.ActivityScope
import com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies.LiveCurrenciesFragmentModule
import com.nstudiosappdev.stocker.dashboard.presentation.portfolio.PortfolioFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [PortfolioFragmentModule::class,
            LiveCurrenciesFragmentModule::class
        ]
    )
    abstract fun provideDashboardActivity(): DashboardActivity
}