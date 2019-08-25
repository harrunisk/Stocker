package com.nstudiosappdev.stocker.injection.modules

import androidx.lifecycle.ViewModelProvider
import com.nstudiosappdev.core.presentation.viewmodel.VmFactory
import dagger.Binds
import dagger.Module

@Module

internal abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(vmFactory: VmFactory): ViewModelProvider.Factory
}