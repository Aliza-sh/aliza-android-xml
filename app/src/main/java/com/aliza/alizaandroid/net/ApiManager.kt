package com.aliza.alizaandroid.net

import com.aliza.alizaandroid.net.model.Student
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    private val apiService: ApiService

    init {

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

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getAllStudents(apiCallback: ApiCallback<List<Student>>) {

        apiService.getAllStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        apiCallback.onSuccess(data)
                    } else {
                        // Handle api null
                        apiCallback.onError("data is null")
                    }
                } else {
                    // Handle api error
                    apiCallback.onError("Error: " + response.code())
                }
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    private fun cleanCoinsData(data: List<Student>): List<Student> {

        val newData = mutableListOf<Student>()

        data.forEach {
            if (it != null) {
                newData.add(it)
            }
        }
        return newData
    }


    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}