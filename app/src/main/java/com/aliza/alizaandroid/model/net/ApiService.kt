package com.aliza.alizaandroid.model.net

import com.google.gson.JsonObject
import com.aliza.alizaandroid.model.data.Student


interface ApiService {

    @GET("/student")
    fun getAllStudents(): Call<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body: JsonObject): Call<Int>

    @PUT("/student/updating{name}")
    fun updateStudent( @Path("name") name:String , @Body body :JsonObject ) :Call<Int>

    @DELETE("/student/deleting{name}")
    fun deleteStudent( @Path("name") name:String ) :Call<Int>
}