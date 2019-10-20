package com.nstudiosappdev.stocker.dashboard.presentation.portfolio

import com.nstudiosappdev.core.injection.scope.FragmentScope
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PortfolioFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrenciesPresentationModule::class])
    abstract fun contributePortfolioFragmentModule(): PortfolioFragment
}
