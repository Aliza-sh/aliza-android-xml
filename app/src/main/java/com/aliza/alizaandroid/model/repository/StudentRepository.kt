package com.aliza.alizaandroid.model.repository

import com.aliza.alizaandroid.core.App
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.net.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentRepository {

    private var apiService: ApiService? = null

    init {
        apiService = App.api
    }

    fun getAllStudents(apiCallback: StudentRepository.ApiCallback<List<Student>>) {

        apiService!!.getAllStudents().enqueue(object : Callback<List<Student>> {
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

    fun deleteStudent(nameStudent: String, apiCallback: StudentRepository.ApiCallback<Int>) {
        apiService!!.deleteStudent(nameStudent).enqueue(object : Callback<Int> {
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