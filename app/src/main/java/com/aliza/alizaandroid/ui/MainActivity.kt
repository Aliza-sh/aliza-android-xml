package com.aliza.alizaandroid.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.model.MainRepository
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)
    private val viewModel = MainViewModel(MainRepository())
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.dataStudents3.collect {
                        Log.v("testFlow" , it.name)
                    }
                }
                launch {
                    viewModel.dataStudents7.collect {
                        Log.v("testFlow" , it.name)
                    }
                }

            }
        }
    }
}