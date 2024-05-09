package com.aliza.alizaandroid.workers.chainWorkers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Exception

class WorkerSaveImag(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            val result = doMyJob()
            Result.success(
                workDataOf(
                    "imageSave" to result
                )
            )

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob(): String {

        val savedImage = inputData.getString("editImage")

        Thread.sleep(5000)

        if (savedImage != null) {
            Log.v(
                "chainWorkers",
                "Saved Image..."
            )
        }
        return "image Saved"
    }


}