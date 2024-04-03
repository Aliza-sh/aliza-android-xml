package com.aliza.alizaandroid

import android.os.Bundle
import com.aliza.alizaandroid.databinding.ActivitySecondBinding
import ir.dunijet.animation.ext.BaseActivity

class SecondActivity : BaseActivity<ActivitySecondBinding>() {
    override fun inflateBinding()= ActivitySecondBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}