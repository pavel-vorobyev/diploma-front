package com.pavelvorobyev.diploma.businesslogic.api

import android.content.Context
import com.pavelvorobyev.diploma.businesslogic.api.interceptos.AuthorizationInterceptor
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiHelper(private val context: Context) {

    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .callTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(AuthorizationInterceptor(context))
            .addInterceptor(logging)
            .build()
    }

    private val api by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.102:9173")
            .client(httpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val apiService: Service by lazy {
        api.create(Service::class.java)
    }
}
