package com.aliza.alizaandroid.ui.addStudent

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityAddStudentBinding
import com.aliza.alizaandroid.di.App
=======
import com.aliza.alizaandroid.model.net.ApiManager
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.utils.EXTRA_STUDENT
import com.aliza.alizaandroid.utils.STUDENT_COURSE
import com.aliza.alizaandroid.utils.STUDENT_NAME
import com.aliza.alizaandroid.utils.STUDENT_SCORE
import com.google.gson.JsonObject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class AddStudentActivity : BaseActivity<ActivityAddStudentBinding>() {
    override fun inflateBinding(): ActivityAddStudentBinding =
        ActivityAddStudentBinding.inflate(layoutInflater)

    private var isInserting = true
    private val apiService = App.api!!
    lateinit var disposable: Disposable
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    private val apiManager = ApiManager()
    private var isInserting = true

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarAddStudentActivity)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.edtFirstName.requestFocus()

        val dataFromIntent = intent.getParcelableExtra(EXTRA_STUDENT, Student::class.java)
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

            apiService
                .insertStudent(jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onError(e: Throwable) {
                        Log.v("testApi", e.message.toString())
                    }

                    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                    override fun onSuccess(t: Int) {
                        showSnackbar(binding.root, "student inserted successfully.").show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 1500)
                    }
                })
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
            val jsonObject = JsonObject()
            jsonObject.addProperty(STUDENT_NAME, "$firstName $lastName")
            jsonObject.addProperty(STUDENT_COURSE, course)
            jsonObject.addProperty(STUDENT_SCORE, score.toInt())

            apiService
                .updateStudent("$firstName $lastName",jsonObject)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SingleObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onError(e: Throwable) {
                        Log.v("testApi", e.message.toString())
                    }

                    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
                    override fun onSuccess(t: Int) {
                        showSnackbar(binding.root, "student inserted successfully.").show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 1500)
                    }
                })

            apiManager.updateStudent(firstName , lastName, jsonObject, object : ApiManager.ApiCallback<Int> {
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