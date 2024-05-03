package com.aliza.alizaandroid.features

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.STUDENT_COURSE
import com.aliza.alizaandroid.STUDENT_NAME
import com.aliza.alizaandroid.STUDENT_SCORE
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.base.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityAddStudentBinding
import com.aliza.alizaandroid.net.ApiManager
import com.google.gson.JsonObject

class AddStudentActivity : BaseActivity<ActivityAddStudentBinding>() {
    override fun inflateBinding(): ActivityAddStudentBinding =
        ActivityAddStudentBinding.inflate(layoutInflater)

    private val apiManager = ApiManager()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAddStudentActivity)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.edtFirstName.requestFocus()

        binding.btnDone.setOnClickListener {
            addNewStudent()
        }

    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    private fun addNewStudent() {
        val firstName = binding.edtFirstName.text.toString()
        val lastName = binding.edtLastName.text.toString()
        val score = binding.edtScore.text.toString()
        val course = binding.edtCourse.text.toString()
        if (
            firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            course.isNotEmpty() &&
            score.isNotEmpty()
        ) {
            val jsonObject = JsonObject()
            jsonObject.addProperty(STUDENT_NAME, "$firstName $lastName")
            jsonObject.addProperty(STUDENT_COURSE, course)
            jsonObject.addProperty(STUDENT_SCORE, score.toInt())
            apiManager.insertStudent(jsonObject, object : ApiManager.ApiCallback<Int> {
                @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                override fun onSuccess(data: Int) {
                    showSnackbar(binding.root, "student inserted successfully.").show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        finish()
                    }, 1500)
                }

                override fun onError(errorMessage: String) {
                    Log.e("testApi", errorMessage)
                }
            })
        } else {
            showSnackbar(binding.root, "Please enter complete information.").show()
        }
    }

}