package com.aliza.alizaandroid

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var workManager: WorkManager
    private lateinit var sampleOneTimeWorker: OneTimeWorkRequest

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. create work manager =>
        workManager = WorkManager.getInstance(this)

        // 2. create our work =>
        sampleOneTimeWorker()

        // 3. work manager should do works =>
        workManager.enqueue(sampleOneTimeWorker)

    }

    private fun sampleOneTimeWorker() {
        sampleOneTimeWorker = OneTimeWorkRequest.from(SampleOneTimeWorker::class.java)
    }
}