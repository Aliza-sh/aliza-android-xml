package com.aliza.alizaandroid.model.repository

import androidx.lifecycle.LiveData
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.db.StudentDao
import com.aliza.alizaandroid.model.net.ApiService
import com.aliza.alizaandroid.utils.studentToJsonObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


class MainRepository(
    private val apiService: ApiService,
    private val studentDao: StudentDao
) {

    fun getAllStudents(): LiveData<List<Student>> {
        return studentDao.getAllData()
    }

    // caching
    fun refreshData(): Completable {
        return apiService
            .getAllStudents()
            .doOnSuccess {
                studentDao.insertAll(it)
            }
            .ignoreElement()
    }

    fun insertStudent(student: Student):  Single<Int> {
        return apiService
            .insertStudent( studentToJsonObject(student) )
            .doOnSuccess{
                studentDao.insertOrUpdate(student)
            }
    }

    fun updateStudent(student: Student):  Single<Int> {
        return apiService
            .updateStudent(student.name, studentToJsonObject(student))
            .doOnSuccess {
                studentDao.insertOrUpdate(student)
            }
    }

    fun deleteStudent(studentName: String): Single<Int> {
        return apiService
            .deleteStudent(studentName)
            .doOnSuccess {
                studentDao.delete(studentName)
            }
    }

}