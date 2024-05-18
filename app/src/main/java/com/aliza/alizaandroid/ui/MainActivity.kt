package com.aliza.alizaandroid.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.di.module.imageModule.ImageLoaderService
import com.aliza.alizaandroid.net.ApiService
import com.aliza.alizaandroid.net.Student
import com.aliza.alizaandroid.utils.URL_IMAGE
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val imageLoaderService : ImageLoaderService by inject()

    private val apiService : ApiService by inject()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        imageLoaderService.load(URL_IMAGE, binding.img)

        api()
    }


    private fun api() {
        apiService.getAllStudents().enqueue(object : Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        binding.txt.text = data.toString()
                    } else {
                        // Handle api null
                        binding.txt.text = "data is null"
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