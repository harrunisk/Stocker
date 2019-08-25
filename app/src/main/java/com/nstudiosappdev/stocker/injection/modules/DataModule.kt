package com.nstudiosappdev.stocker.injection.modules

import com.nstudiosappdev.core.data.modules.ApiModule
import dagger.Module

@Module(
    includes = [ApiModule::class]
)
internal abstract class DataModule