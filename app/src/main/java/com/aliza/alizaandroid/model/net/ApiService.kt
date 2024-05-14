package com.aliza.alizaandroid.model.net

import com.google.gson.JsonObject
import com.aliza.alizaandroid.model.data.Student
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface ApiService {

    @GET("/student")
    fun getAllStudents(): Single<List<Student>>

    @POST("/student")
    fun insertStudent(@Body body: JsonObject): Single<Int>

    @PUT("/student/updating{name}")
    fun updateStudent( @Path("name") name:String , @Body body :JsonObject ) :Single<Int>

    @DELETE("/student/deleting{name}")
    fun deleteStudent( @Path("name") name:String ) :Single<Int>
}