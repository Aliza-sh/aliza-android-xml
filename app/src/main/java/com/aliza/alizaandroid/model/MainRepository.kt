package com.aliza.alizaandroid.model

import com.aliza.alizaandroid.model.data.Student
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepository {

    private fun randomStudent(): Student {
        val id = (1..10000).random()
        return Student(
            id = id,
            name = "Student$id",
            familyName = "familyName$id",
            fatherName = "father$id",
            university = "Ferdowsi",
            grade = "grade$id",
            sitePassword = "$id && $id",
            siteUsername = "$id"
        )
    }

    fun getAllFromApi(): Flow<Student> = flow {
        while (true) {
            emit(randomStudent())
            delay(10)
        }
    }
}