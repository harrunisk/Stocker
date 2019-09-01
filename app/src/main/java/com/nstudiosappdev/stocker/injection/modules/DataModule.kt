package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.data.modules.ApiModule
import com.nstudiosappdev.stocker.dashboard.data.CurrenciesDataModule
import dagger.Module

@Module(
    includes = [ApiModule::class,
        CurrenciesDataModule::class]
)
internal abstract class DataModule