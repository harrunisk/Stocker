package com.nstudiosappdev.stocker.dashboard.presentation.liveCurrencies

import com.nstudiosappdev.core.injection.scope.FragmentScope
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesPresentationModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LiveCurrenciesFragmentModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [CurrenciesPresentationModule::class])
    abstract fun contributeCurrenciesFragment(): LiveCurrenciesFragment
}
