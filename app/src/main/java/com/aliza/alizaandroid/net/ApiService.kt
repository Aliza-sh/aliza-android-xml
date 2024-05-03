package com.aliza.alizaandroid.net

import com.google.gson.JsonObject
import com.aliza.alizaandroid.net.model.Student
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/student")
    fun getAllStudents(): Call<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body: JsonObject): Call<Int>

    @PUT("/student/updating{name}")
    fun updateStudent( @Path("name") name:String , @Body body :JsonObject ) :Call<Int>
}