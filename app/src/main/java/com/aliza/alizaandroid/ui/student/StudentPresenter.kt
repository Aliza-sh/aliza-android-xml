package com.aliza.alizaandroid.ui.student

import android.util.Log
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.StudentRepository

class StudentPresenter(private val studentRepository: StudentRepository) : StudentContract.Presenter {

    private var instanceView: StudentContract.View? = null
    override fun onAttach(view: StudentContract.View) {
        instanceView = view
        studentRepository.getAllStudents(object : StudentRepository.ApiCallback<List<Student>> {
            override fun onSuccess(data: List<Student>) {
                instanceView!!.showStudent(data)
            }

            override fun onError(errorMessage: String) {
                Log.v("testApi", errorMessage)
            }
        })
    }

    override fun onDetach() {
        instanceView = null
    }

    override fun onDeleteStudent(student: Student, pos: Int) {
        studentRepository.deleteStudent(student.name, object : StudentRepository.ApiCallback<Int> {
            override fun onSuccess(data: Int) {
                instanceView!!.deleteStudent(student, pos)
            }

            override fun onError(errorMessage: String) {
                Log.v("testApi", errorMessage)
                instanceView!!.errorDeleteStudent(errorMessage)
            }
        })
    }

}