package com.aliza.alizaandroid.model.net

import com.aliza.alizaandroid.model.data.Student
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/student")
    fun getAllStudents() : Call<List<Student>>

}