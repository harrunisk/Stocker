package com.nstudiosappdev.core.data.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nstudiosappdev.core.data.BuildConfig
import com.nstudiosappdev.core.data.api.ApiConstants
import com.nstudiosappdev.core.data.api.interceptor.DefaultRequestInterceptor
import dagger.Module
import dagger.Provides
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    @Singleton
    @Named(NAME_URL)
    fun provideBaseUrl(): String = "http://138.68.103.38:3000/currency_type/"

    @Provides
    @Singleton
    fun provideReqestInterceptor(): Interceptor = DefaultRequestInterceptor()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        requestInterceptor: DefaultRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(requestInterceptor)
            if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)
            connectTimeout(ApiConstants.TIMEOUT_INMILIS, TimeUnit.MILLISECONDS)
            build()
        }

    @Provides
    @Singleton
    fun provideRetrofit(@Named(NAME_URL) baseUrl: String, client: OkHttpClient): Retrofit =
        with(Retrofit.Builder()) {
            baseUrl(baseUrl)
            client(client)
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            build()
        }

    companion object {
        private const val NAME_URL = "url"
    }
}
