package com.aliza.alizaandroid.core

import android.app.Application
import com.aliza.alizaandroid.model.BASE_URL
import com.aliza.alizaandroid.model.net.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App:Application() {

    companion object {
        @JvmStatic
        var api: ApiService? = null
    }

    override fun onCreate() {
        super.onCreate()

        val okHttpClient = OkHttpClient.Builder().addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            //custom Request
            it.proceed(newRequest.build())
        }.build()

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        api = retrofit.create(ApiService::class.java)

    }

}