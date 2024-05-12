package com.aliza.alizaandroid.di

import android.app.Application
import com.aliza.alizaandroid.model.net.ApiService
import com.aliza.alizaandroid.model.net.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        api = retrofit.create(ApiService::class.java)

    }

}