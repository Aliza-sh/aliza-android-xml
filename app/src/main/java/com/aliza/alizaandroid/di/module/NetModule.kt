package com.aliza.alizaandroid.di.module

import com.aliza.alizaandroid.net.ApiService
import com.aliza.alizaandroid.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetModule {

    private fun createRetrofit(): Retrofit {
         val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
    fun createApiService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }
}