package com.aliza.alizaandroid.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliza.alizaandroid.net.ApiService
import com.aliza.alizaandroid.net.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val apiService: ApiService): ViewModel() {

    private var _Student = MutableLiveData<List<Student>>()
    val student get() : LiveData<List<Student>> = _Student

    fun getStudent(){
        apiService.getAllStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _Student.postValue(data)
                    } else {
                        // Handle api null
                        Log.v("errorApi", "data is null")
                    }
                } else {
                    // Handle api error
                    Log.v("errorApi", response.code().toString())
                }
            }
            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Log.v("errorApi", t.message!!)
            }
        })
    }
}