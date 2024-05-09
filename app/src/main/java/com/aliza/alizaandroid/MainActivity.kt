package com.aliza.alizaandroid

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var workManager: WorkManager
    private lateinit var sampleOneTimeWorker: OneTimeWorkRequest
    private lateinit var sendAndReceiveDataOneTimeWorker: OneTimeWorkRequest

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. create work manager =>
        workManager = WorkManager.getInstance(this)

        // 2. create our work =>
        sampleOneTimeWorker()
        sendAndReceiveDataOneTimeWorker()

        // 3. work manager should do works =>
        workManager.enqueue(sampleOneTimeWorker)

        workManager.enqueue(sendAndReceiveDataOneTimeWorker)
        workManager
            .getWorkInfosByTagLiveData("sendAndReceiveDataOneTimeWorker")
            .observe(this) {

                val userWorkerInfo = it[0]!!
                if (userWorkerInfo.state == WorkInfo.State.SUCCEEDED) {
                    val data = userWorkerInfo.outputData.getString("resultOfSendDataOneTimeWorker")
                    if (data != null){
                        Log.v("SendDataOneTimeWorker", data)
                    }
                }
            }
    }

    private fun sampleOneTimeWorker() {
        sampleOneTimeWorker = OneTimeWorkRequest.from(SampleOneTimeWorker::class.java)
    }

    private fun sendAndReceiveDataOneTimeWorker() {
        sendAndReceiveDataOneTimeWorker =
            OneTimeWorkRequestBuilder<SendAndReceiveDataOneTimeWorker>()
                .setInputData(
                    workDataOf(
                        "name" to "aliza",
                        "familyName" to "shahsavari",
                        "pageAddress" to "aliza_Sh",
                        "telegram" to "aliza_Sh",
                        "age" to 21
                    )
                )
                .addTag("sendAndReceiveDataOneTimeWorker")
                .build()
    }
}