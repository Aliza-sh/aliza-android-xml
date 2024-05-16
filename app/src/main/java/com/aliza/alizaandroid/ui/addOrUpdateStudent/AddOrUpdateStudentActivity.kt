package com.aliza.alizaandroid.ui.addOrUpdateStudent

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.aliza.alizaandroid.EXTRA_STUDENT
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityAddOrUpdateStudentBinding
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.db.AppDatabase
import com.aliza.alizaandroid.model.net.ApiServiceSingleton
import com.aliza.alizaandroid.model.repository.MainRepository
import com.aliza.alizaandroid.utils.AddOrUpdateStudentViewModelFactory
import com.aliza.alizaandroid.utils.asyncRequest
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class AddOrUpdateStudentActivity : BaseActivity<ActivityAddOrUpdateStudentBinding>() {
    override fun inflateBinding(): ActivityAddOrUpdateStudentBinding =
        ActivityAddOrUpdateStudentBinding.inflate(layoutInflater)

    private var isInserting = true

    private lateinit var addOrUpdateStudentViewModel: AddOrUpdateStudentViewModel
    private val compositeDisposable = CompositeDisposable()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addOrUpdateStudentViewModel =
            ViewModelProvider(
            this,
            AddOrUpdateStudentViewModelFactory(
                MainRepository(
                    ApiServiceSingleton.apiService!!,
                    AppDatabase.getDatabase(applicationContext).studentDao
                )
            )
        )[AddOrUpdateStudentViewModel::class.java]

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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
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
            val student = Student(
                name = "$firstName $lastName",
                score = score.toInt(),
                course = course
            )
            addOrUpdateStudentViewModel
                .insertStudent(student)
                .asyncRequest()
                .subscribe(object : SingleObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }
                    override fun onError(e: Throwable) {
                        Log.e("testApi", e.message ?: "null")
                    }
                    @SuppressLint("NewApi")
                    override fun onSuccess(t: Int) {
                        showSnackbar(binding.root, "student inserted successfully.").show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 1500)
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
            val student = Student(
                name = "$firstName $lastName",
                score = score.toInt(),
                course = course
            )
            addOrUpdateStudentViewModel
                .updateStudent(student)
                .asyncRequest()
                .subscribe(object : SingleObserver<Int> {
                    override fun onSubscribe(d: Disposable) {
                        compositeDisposable.add(d)
                    }
                    override fun onError(e: Throwable) {
                        Log.e("testApi", e.message ?: "null")
                    }
                    @SuppressLint("NewApi")
                    override fun onSuccess(t: Int) {
                        showSnackbar(binding.root, "student updated successfully.").show()
                        Handler(Looper.getMainLooper()).postDelayed({
                            finish()
                        }, 1500)
                    }
                })
        } else {
            showSnackbar(binding.root, "Please enter complete information.").show()
        }
    }
}