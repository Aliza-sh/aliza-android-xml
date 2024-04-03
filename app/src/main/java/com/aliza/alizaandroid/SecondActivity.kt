package com.aliza.alizaandroid

import android.os.Bundle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivitySecondBinding

class SecondActivity : BaseActivity<ActivitySecondBinding>() {
    override fun inflateBinding() = ActivitySecondBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}