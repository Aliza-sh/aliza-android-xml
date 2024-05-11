package com.aliza.alizaandroid.workers.chainWorkers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Exception

class WorkerDownLoadImag(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            val result = doMyJob()

            Result.success(
                workDataOf(
                    "image" to result
                )
            )

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob(): String {

        val urlImage = inputData.getString("urlImage")

        Thread.sleep(5000)

        if (urlImage != null) {
            Log.v(
                "chainWorkers",
                "DownLoad Image..."
            )
        }
        return "image"
    }

}