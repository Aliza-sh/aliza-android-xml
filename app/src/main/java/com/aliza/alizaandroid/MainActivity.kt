package com.aliza.alizaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}