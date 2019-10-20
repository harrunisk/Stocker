package com.nstudiosappdev.core.data.api.interceptor

import com.nstudiosappdev.core.data.BuildConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().newBuilder()) {
            addHeader("Content-Type", "application/json")
            addHeader("VersionCode", BuildConfig.VERSION_CODE.toString())
            addHeader("VersionName", BuildConfig.VERSION_NAME)
            addHeader("ApplicationId", BuildConfig.APPLICATION_ID)
            build()
        })
    }
}
