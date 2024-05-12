package com.aliza.alizaandroid.ui.student

import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.base.BasePresenter
import com.aliza.alizaandroid.base.BaseView

interface StudentContract {

    interface Presenter : BasePresenter<View> {
        fun onDeleteStudent(student: Student, pos: Int)
    }

    interface View : BaseView {
        fun showStudent(data: List<Student>)
        fun deleteStudent(oldStudent: Student, pos: Int)
        fun errorDeleteStudent(errorMessage: String)

    }

}