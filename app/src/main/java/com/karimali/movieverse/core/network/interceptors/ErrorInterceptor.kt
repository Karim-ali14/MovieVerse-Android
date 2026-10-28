package com.karimali.movieverse.core.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ErrorInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        when (response.code) {
            401 -> { /* Handle Unauthorized (e.g. Refresh Token or Logout) */ }
            403 -> { /* Handle Access Denied */ }
            500 -> { /* Handle Server Error */ }
        }

        return response
    }
}