package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.data.modules.CoreDataModule
import com.nstudiosappdev.stocker.dashboard.data.CurrenciesDataModule
import dagger.Module

@Module(
    includes = [CoreDataModule::class,
        CurrenciesDataModule::class]
)
internal abstract class DataModule
