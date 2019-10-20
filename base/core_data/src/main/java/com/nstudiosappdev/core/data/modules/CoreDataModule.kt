package com.nstudiosappdev.core.data.modules

import com.nstudiosappdev.core.data.adapter.ApiCallAdapter
import com.nstudiosappdev.core.data.adapter.CallAdapter
import com.nstudiosappdev.core.data.api.response.ApiResponse
import com.nstudiosappdev.core.error.ErrorFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlinx.coroutines.Deferred

@Module(includes = [ApiModule::class, DbModule::class])
class CoreDataModule {
    @Singleton
    @Provides
    fun provideApiCallAdapter(errorFactory: ErrorFactory): CallAdapter<Deferred<ApiResponse<out Any?>>> =
            ApiCallAdapter(errorFactory)
}
