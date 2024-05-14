package com.aliza.alizaandroid.ui.student

import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.MainRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class StudentViewModel(
    private val mainRepository: MainRepository
) {
    val progressBarSubject = BehaviorSubject.create<Boolean>()

    fun getAllStudents(): Single<List<Student>> {
        progressBarSubject.onNext(true)

        return mainRepository
            .getAllStudents()
            .delay(1, TimeUnit.SECONDS)
            .doFinally {
                progressBarSubject.onNext(false)
            }
    }

    fun deleteStudent(studentName: String): Single<Int> {
        return mainRepository.deleteStudent(studentName)
    }

}