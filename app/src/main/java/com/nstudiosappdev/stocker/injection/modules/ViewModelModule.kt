package com.nstudiosappdev.stocker.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.nstudiosappdev.core.presentation.viewmodel.VmFactory
import com.nstudiosappdev.stocker.dashboard.presentation.CurrenciesViewModelModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [CurrenciesViewModelModule::class]
)

internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}
