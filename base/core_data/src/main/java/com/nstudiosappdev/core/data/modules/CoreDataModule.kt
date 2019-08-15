package com.nstudiosappdev.core.data.modules

import com.nstudiosappdev.core.data.adapter.ApiCallAdapter
import com.nstudiosappdev.core.data.adapter.CallAdapter
import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.error.ErrorFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Deferred
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class CoreDataModule {
    @Singleton
    @Provides
    fun provideApiCallAdapter(errorFactory: ErrorFactory): CallAdapter<Deferred<ApiResponse<out Any?>>> =
            ApiCallAdapter(errorFactory)
}