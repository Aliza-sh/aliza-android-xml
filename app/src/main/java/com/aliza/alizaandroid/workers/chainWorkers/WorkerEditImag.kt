package com.aliza.alizaandroid.workers.chainWorkers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Exception

class WorkerEditImag(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            val result = doMyJob()
            Result.success(
                workDataOf(
                    "editImage" to result
                )
            )

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob(): String {

        val editedImage = inputData.getString("image")

        Thread.sleep(5000)

        if (editedImage != null) {
            Log.v(
                "chainWorkers",
                "Edit Image..."
            )
        }
        return "image edited"
    }

}