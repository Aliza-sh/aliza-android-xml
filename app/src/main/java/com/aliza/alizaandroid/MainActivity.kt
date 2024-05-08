package com.aliza.alizaandroid

import android.content.Intent
import android.os.Bundle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startBackgroundService()

    }

    private fun startBackgroundService() {
        val intent = Intent(this , BackgroundService::class.java)
        startService(intent)
    }

}