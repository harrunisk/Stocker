package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.stocker.dashboard.domain.CurrenciesDomainModule
import dagger.Module

@Module(
    includes = [CurrenciesDomainModule::class]
)
internal abstract class DomainModule