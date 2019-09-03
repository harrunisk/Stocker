package com.nstudiosappdev.stocker.dashboard.presentation.currencies

import com.nstudiosappdev.core.injection.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CurrenciesFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrenciesPresentationModule::class])
    abstract fun contributeCurrenciesFragment(): CurrenciesFragment
}