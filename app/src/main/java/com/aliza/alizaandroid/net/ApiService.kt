package com.aliza.alizaandroid.net

import com.aliza.alizaandroid.net.model.BodyStudent
import com.aliza.alizaandroid.net.model.ResponseStudent
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/student")
    fun getAllStudents(): Call<List<ResponseStudent>>

    @POST("/student")
    fun insertStudent(@Body body: BodyStudent): Call<Int>

    @PUT("/student/updating{name}")
    fun updateStudent( @Path("name") name:String , @Body body :BodyStudent ) :Call<Int>

    @DELETE("/student/deleting{name}")
    fun deleteStudent( @Path("name") name:String ) :Call<Int>
}