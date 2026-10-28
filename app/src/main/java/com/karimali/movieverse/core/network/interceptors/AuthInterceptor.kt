package com.karimali.movieverse.core.network.interceptors

import com.karimali.movieverse.core.common.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer ${Constants.AUTH_TOKEN}")
            .addHeader("accept","application/json")
            .build()
        return chain.proceed(request)
    }
}