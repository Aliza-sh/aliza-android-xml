package com.aliza.alizaandroid.model.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceSingleton {
    var apiService: ApiService? = null
        get() {
            if (field == null) {

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

                field = retrofit.create(ApiService::class.java)
            }
            return field
        }
}