package com.aliza.alizaandroid.model.net

import com.aliza.alizaandroid.model.data.Student
import com.google.gson.JsonObject
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

    fun insertStudent(body: JsonObject, apiCallback: ApiCallback<Int>) {
        apiService.insertStudent(body).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
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
            override fun onFailure(call: Call<Int>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    fun updateStudent(
        firstName: String,
        lastName: String,
        body: JsonObject,
        apiCallback: ApiCallback<Int>
    ) {
        apiService.updateStudent("$firstName $lastName", body).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
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
            override fun onFailure(call: Call<Int>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    fun deleteStudent(nameStudent: String, apiCallback: ApiCallback<Int>) {
        apiService.deleteStudent(nameStudent).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
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
            override fun onFailure(call: Call<Int>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    interface ApiCallback<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }
}