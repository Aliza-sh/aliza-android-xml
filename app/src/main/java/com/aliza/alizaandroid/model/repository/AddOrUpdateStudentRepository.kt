package com.aliza.alizaandroid.model.repository

import com.aliza.alizaandroid.core.App
import com.aliza.alizaandroid.model.net.ApiService
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddOrUpdateStudentRepository {

    private var apiService: ApiService? = null

    init {
        apiService = App.api
    }

    fun insertStudent(body: JsonObject, apiCallback: ApiCallback<Int>) {
        apiService!!.insertStudent(body).enqueue(object : Callback<Int> {
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
        name: String,
        body: JsonObject,
        apiCallback: ApiCallback<Int>
    ) {
        apiService!!.updateStudent(name, body).enqueue(object : Callback<Int> {
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