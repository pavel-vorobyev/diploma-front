package com.pavelvorobyev.diploma.businesslogic.api.interceptos

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.store.Store
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(context: Context) : Interceptor {

    private val store = Store(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = store.token
        val request = chain.request().newBuilder()
            .addHeader("Authorization", token)

        return chain.proceed(request.build())
    }
}
