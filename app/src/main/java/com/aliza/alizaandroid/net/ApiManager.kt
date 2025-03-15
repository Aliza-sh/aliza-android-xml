package com.aliza.alizaandroid.net

import com.aliza.alizaandroid.net.model.BodyStudent
import com.aliza.alizaandroid.net.model.ResponseStudent
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

    fun getAllStudents(apiCallback: ApiCallback<List<ResponseStudent>>) {

        apiService.getAllStudents().enqueue(object : Callback<List<ResponseStudent>> {
            override fun onResponse(
                call: Call<List<ResponseStudent>>,
                response: Response<List<ResponseStudent>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { itBody ->
                        itBody.let { itData ->
                            if (itData.isNotEmpty())
                                apiCallback.onSuccess(itData)
                            else
                                apiCallback.onError("data is null")
                        }
                    }
                } else {
                    // Handle api error
                    apiCallback.onError("Error: " + response.code())
                }
            }

            override fun onFailure(call: Call<List<ResponseStudent>>, t: Throwable) {
                apiCallback.onError(t.message!!)
            }
        })
    }

    private fun cleanCoinsData(data: List<ResponseStudent>): List<ResponseStudent> {

        val newData = mutableListOf<ResponseStudent>()

        data.forEach {
            if (it != null) {
                newData.add(it)
            }
        }
        return newData
    }

    fun insertStudent(body: BodyStudent, apiCallback: ApiCallback<Int>) {
        apiService.insertStudent(body).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    response.body()?.let { itBody ->
                        itBody.let { itData ->
                            apiCallback.onSuccess(itData)
                        }
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
        body: BodyStudent,
        apiCallback: ApiCallback<Int>
    ) {
        apiService.updateStudent("$firstName $lastName", body).enqueue(object : Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful) {
                    response.body()?.let { itBody ->
                        itBody.let { itData ->
                            apiCallback.onSuccess(itData)
                        }
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
                    response.body()?.let { itBody ->
                        itBody.let { itData ->
                            apiCallback.onSuccess(itData)
                        }
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