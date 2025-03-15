package com.aliza.alizaandroid.features

import android.R
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.EXTRA_STUDENT
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.base.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityAddStudentBinding
import com.aliza.alizaandroid.net.ApiManager
import com.aliza.alizaandroid.net.model.BodyStudent
import com.aliza.alizaandroid.net.model.ResponseStudent

class AddStudentActivity : BaseActivity<ActivityAddStudentBinding>() {
    override fun inflateBinding(): ActivityAddStudentBinding =
        ActivityAddStudentBinding.inflate(layoutInflater)

    private val apiManager = ApiManager()
    private var isInserting = true

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAddStudentActivity)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.edtFirstName.requestFocus()

        val dataFromIntent = intent.getParcelableExtra(EXTRA_STUDENT, ResponseStudent::class.java)
        isInserting = (dataFromIntent == null)
        if (!isInserting) {
            binding.btnDone.text = "update"
            binding.edtScore.setText(dataFromIntent!!.score.toString())
            binding.edtCourse.setText(dataFromIntent.course)
            val splittedName = dataFromIntent.name.split(" ")
            binding.edtFirstName.setText(splittedName[0])
            binding.edtLastName.setText(splittedName[(splittedName.size - 1)])
        }
        binding.btnDone.setOnClickListener {
            if (isInserting) {
                addNewStudent()
            } else {
                updateStudent()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.home) {
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
            val student = BodyStudent(
                name = "$firstName $lastName",
                course = course,
                score = score.toInt()
            )
            apiManager.insertStudent(student, object : ApiManager.ApiCallback<Int> {
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

    private fun updateStudent() {
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

            val student = BodyStudent(
                name = "$firstName $lastName",
                course = course,
                score = score.toInt()
            )
            apiManager.updateStudent(firstName , lastName, student, object : ApiManager.ApiCallback<Int> {
                @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                override fun onSuccess(data: Int) {
                    showSnackbar(binding.root, "student updated successfully.").show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        finish()
                    }, 1500)
                }
                override fun onError(errorMessage: String) {
                    Log.v("testApi", errorMessage)
                }
            })
        } else {
            showSnackbar(binding.root,"Please enter complete information.").show()
        }
    }


}