package com.aliza.alizaandroid

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OverwritingInputMerger
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.chainWorkers.WorkerDownLoadImag
import com.aliza.alizaandroid.chainWorkers.WorkerEditImag
import com.aliza.alizaandroid.chainWorkers.WorkerSaveImag
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

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
//************************************************************

        // 2. create our work =>
        sampleOneTimeWorker()
        sendAndReceiveDataOneTimeWorker()
//************************************************************

        // 3. work manager should do works =>
        //workManager.enqueue(sampleOneTimeWorker)

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

        requestNotificationPermission{
            periodicWorker()
        }
//************************************************************
        /*workManager
            .beginWith(OneTimeWorkRequest.from(WorkerDownLoadImag::class.java))
            .then(OneTimeWorkRequest.from(WorkerEditImag::class.java))
            .then(OneTimeWorkRequest.from(WorkerSaveImag::class.java))
            .enqueue()*/

        val downLoadImagRequest = OneTimeWorkRequest.Builder(WorkerDownLoadImag::class.java)
            .setInputData(
                workDataOf(
                    "urlImage" to "https://www.google.com",
                )
            )
            .build()

        val editImagWorkRequest = OneTimeWorkRequest.Builder(WorkerEditImag::class.java)
            .setInputMerger(OverwritingInputMerger::class.java)
            .build()

        val saveImagWorkRequest = OneTimeWorkRequest.Builder(WorkerSaveImag::class.java)
            .setInputMerger(OverwritingInputMerger::class.java)
            .build()

        workManager
            .beginUniqueWork("chainWorkers",ExistingWorkPolicy.KEEP,downLoadImagRequest)
            .then(editImagWorkRequest)
            .then(saveImagWorkRequest)
            .enqueue()

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

    private fun periodicWorker() {
        workManager.enqueue(
            PeriodicWorkRequestBuilder<PeriodicWorker>(
                1, TimeUnit.MINUTES,
            )
                .addTag("periodicWorker")
                .build()
        )
    }
    private fun requestNotificationPermission(onPermissionGranted: () -> Unit) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(POST_NOTIFICATIONS),
                    1
                )
            } else {
                onPermissionGranted()
            }
        } else {
            //Handle notification permission request for pre-Android 13 versions (if needed)
        }
    }
}