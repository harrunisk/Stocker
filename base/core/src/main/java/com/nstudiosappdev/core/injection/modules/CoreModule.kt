package com.nstudiosappdev.core.injection.modules

import com.nstudiosappdev.core.date.DateTimeConverter
import com.nstudiosappdev.core.date.DefaultDateTimeConverter
import com.nstudiosappdev.core.preconditions.AndroidPreConditions
import com.nstudiosappdev.core.preconditions.DefaultAndroidPreConditions
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [CoroutineManagerModule::class,
        CoroutineDispatcherModule::class,
        ErrorFactoryModule::class]
)
class CoreModule {

    @Provides
    @Singleton
    fun provideAndroidPreConditions(): AndroidPreConditions = DefaultAndroidPreConditions()

    @Provides
    @Singleton
    fun provideDateTimeConverter(): DateTimeConverter = DefaultDateTimeConverter()
}
