package com.aliza.alizaandroid.net

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/student")
    fun getAllStudents(): Call<List<Student>>
}