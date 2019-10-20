package com.nstudiosappdev.core.injection.modules

import android.content.Context
import com.nstudiosappdev.core.error.DefaultErrorFactory
import com.nstudiosappdev.core.error.ErrorFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ErrorFactoryModule {

    @Provides
    @Singleton
    internal fun provideErrorFactory(context: Context): ErrorFactory =
            DefaultErrorFactory(context.applicationContext)
}
