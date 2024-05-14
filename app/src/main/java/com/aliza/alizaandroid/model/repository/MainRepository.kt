package com.aliza.alizaandroid.model.repository

import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.net.ApiService
import com.aliza.alizaandroid.model.net.BASE_URL
import com.aliza.alizaandroid.utils.studentToJsonObject
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository {
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
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

    }

    fun getAllStudents(): Single<List<Student>> {
        return apiService.getAllStudents()
    }

    fun deleteStudent(studentName: String): Single<Int> {
        return apiService.deleteStudent(studentName)
    }

    fun insertStudent(student: Student): Single<Int> {
        return apiService.insertStudent(studentToJsonObject(student))
    }

    fun updateStudent(student: Student): Single<Int> {
        return apiService.updateStudent(student.name, studentToJsonObject(student))
    }

}