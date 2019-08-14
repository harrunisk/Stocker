package com.nstudiosappdev.core.injection.modules

import com.nstudiosappdev.core.coroutines.DefaultDispatcherProvider
import com.nstudiosappdev.core.coroutines.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoroutineDispatcherModule {

    @Provides
    @Singleton
    fun provideDefaultDispatcher(): DispatcherProvider = DefaultDispatcherProvider()
}