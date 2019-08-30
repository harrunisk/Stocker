package com.nstudiosappdev.stocker.dashboard.presentation

import com.nstudiosappdev.core.injection.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CurrenciesFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrenciesPresentationModule::class])
    abstract fun contributeCurrenciesFragment(): CurrenciesFragment
}