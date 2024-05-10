package com.aliza.alizaandroid.ui.addOrUpdateStudent

import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.base.BasePresenter
import com.aliza.alizaandroid.base.BaseView

interface AddOrUpdateStudentContract {

    interface Presenter : BasePresenter<View> {
        fun onAddNewStudent(student: Student)
        fun onUpdateStudent(student: Student)
    }

    interface View : BaseView {

        fun successAddNewStudent()
        fun errorAddNewStudent(errorMessage: String)
        fun successUpdateStudent()
        fun errorUpdateStudent(errorMessage: String)

    }

}