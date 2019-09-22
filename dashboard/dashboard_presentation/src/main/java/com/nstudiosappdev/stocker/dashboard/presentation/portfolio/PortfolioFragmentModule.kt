package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.injection.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PortfolioFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [PortfolioPresentationModule::class])
    abstract fun contributePortfolioFragmentModule(): PortfolioFragment
}