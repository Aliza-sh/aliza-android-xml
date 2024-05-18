package com.aliza.alizaandroid.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.di.module.imageModule.ImageLoaderService
import com.aliza.alizaandroid.utils.URL_IMAGE
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val imageLoaderService : ImageLoaderService by inject()

    private val viewModel : MainViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        imageLoaderService.load(URL_IMAGE, binding.img)

        viewModel.getStudent()
        viewModel.student.observe(this) {
            binding.txt.text = it.toString()
        }    }
}