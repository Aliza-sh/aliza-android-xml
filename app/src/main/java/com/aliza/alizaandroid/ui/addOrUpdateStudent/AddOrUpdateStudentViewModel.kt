package com.aliza.alizaandroid.ui.addOrUpdateStudent

import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.MainRepository
import io.reactivex.rxjava3.core.Single


class AddOrUpdateStudentViewModel(private val mainRepository: MainRepository) {

    fun insertStudent(student: Student): Single<Int> {
        return mainRepository.insertStudent(student)
    }

    fun updateStudent(student: Student): Single<Int> {
        return mainRepository.updateStudent(student)
    }

}