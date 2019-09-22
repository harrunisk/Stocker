package com.nstudiosappdev.stocker.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.nstudiosappdev.core.presentation.viewmodel.VmFactory
import com.nstudiosappdev.stocker.dashboard.presentation.currencies.CurrenciesViewModelModule
import com.nstudiosappdev.stocker.dashboard.presentation.portfolio.PortfolioViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [CurrenciesViewModelModule::class, PortfolioViewModelModule::class]
)

internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}