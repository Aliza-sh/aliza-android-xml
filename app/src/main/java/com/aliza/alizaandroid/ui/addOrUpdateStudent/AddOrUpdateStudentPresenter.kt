package com.aliza.alizaandroid.ui.addOrUpdateStudent

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.AddOrUpdateStudentRepository
import com.aliza.alizaandroid.utils.STUDENT_COURSE
import com.aliza.alizaandroid.utils.STUDENT_NAME
import com.aliza.alizaandroid.utils.STUDENT_SCORE
import com.google.gson.JsonObject

class AddOrUpdateStudentPresenter(private val sddOrUpdateStudentRepository: AddOrUpdateStudentRepository) :
    AddOrUpdateStudentContract.Presenter {
    private var instanceView: AddOrUpdateStudentContract.View? = null

    override fun onAttach(view: AddOrUpdateStudentContract.View) {
        instanceView = view
    }

    override fun onDetach() {
        instanceView = null
    }

    override fun onAddNewStudent(student: Student) {
        val jsonObject = JsonObject()
        jsonObject.addProperty(STUDENT_NAME, student.name)
        jsonObject.addProperty(STUDENT_COURSE, student.course)
        jsonObject.addProperty(STUDENT_SCORE, student.score)

        sddOrUpdateStudentRepository.insertStudent(
            jsonObject,
            object : AddOrUpdateStudentRepository.ApiCallback<Int> {
                @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                override fun onSuccess(data: Int) {
                    instanceView!!.successAddNewStudent()
                }

                override fun onError(errorMessage: String) {
                    Log.e("testApi", errorMessage)
                    instanceView!!.errorAddNewStudent(errorMessage)

                }
            })
    }

    override fun onUpdateStudent(student: Student) {
        val jsonObject = JsonObject()
        jsonObject.addProperty(STUDENT_NAME, student.name)
        jsonObject.addProperty(STUDENT_COURSE, student.course)
        jsonObject.addProperty(STUDENT_SCORE, student.score)

        sddOrUpdateStudentRepository.updateStudent(
            student.name,
            jsonObject,
            object : AddOrUpdateStudentRepository.ApiCallback<Int> {
                @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                override fun onSuccess(data: Int) {
                    instanceView!!.successUpdateStudent()
                }

                override fun onError(errorMessage: String) {
                    Log.v("testApi", errorMessage)
                    instanceView!!.errorUpdateStudent(errorMessage)
                }
            })
    }

}