package com.aliza.alizaandroid

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Exception

class SendAndReceiveDataOneTimeWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            val result = doMyJob()
            Result.success(
                workDataOf(
                    "resultOfSendDataOneTimeWorker" to result
                )
            )

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob(): String {

        val map = inputData.keyValueMap

        val firstName = inputData.getString("name")
        val lastName = map["familyName"]
        val myPage = inputData.getString("pageAddress")
        val telegramId = map["telegram"]
        val age = inputData.getInt("age", 0)

        val result = "$firstName $lastName $myPage $telegramId $age"

        Log.v(
            "ReceiveDataOneTimeWorker",
            result
        )

        return result
    }

}