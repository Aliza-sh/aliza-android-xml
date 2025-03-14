package com.aliza.alizaandroid.ui.pages1

import android.os.Bundle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityPages1Binding

class Pages1Activity : BaseActivity<ActivityPages1Binding>() {
    override fun inflateBinding() = ActivityPages1Binding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}