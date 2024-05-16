package com.aliza.alizaandroid.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.model.net.ApiService
import com.aliza.alizaandroid.model.data.Student
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    @Inject lateinit var apiService: ApiService

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        apiService
            .getAllStudents()
            .enqueue( object : Callback<List<Student>> {

                override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                    Log.v("testLog" , response.body().toString() )
                }

                override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                    Log.v("testLog" , t.message ?: "null")
                }

            } )

    }
}