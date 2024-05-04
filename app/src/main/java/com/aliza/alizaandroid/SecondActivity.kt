package com.aliza.alizaandroid

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.base.INTENT_NOTIFICATION
import com.aliza.alizaandroid.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {
    override fun inflateBinding(): ActivitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = intent.getStringExtra(INTENT_NOTIFICATION).toString()
        binding.textView.text = data
    }
}