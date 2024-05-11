package com.aliza.alizaandroid.ui.addOrUpdateStudent

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.utils.EXTRA_STUDENT
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityAddStudentBinding
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.AddOrUpdateStudentRepository


class AddOrUpdateStudentActivity : BaseActivity<ActivityAddStudentBinding>(),
    AddOrUpdateStudentContract.View {
    override fun inflateBinding(): ActivityAddStudentBinding =
        ActivityAddStudentBinding.inflate(layoutInflater)

    private lateinit var presenter: AddOrUpdateStudentContract.Presenter
    private var isInserting = true

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = AddOrUpdateStudentPresenter(AddOrUpdateStudentRepository())
        presenter.onAttach(this)

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
        presenter.onDetach()
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
            val newStudent = Student(
                name = "$firstName $lastName",
                course = course,
                score = score.toInt()
            )
            presenter.onAddNewStudent(newStudent)
        } else {
            showSnackbar(binding.root, "Please enter complete information.").show()
        }
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun successAddNewStudent() {
        showSnackbar(binding.root, "student inserted successfully.").show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1500)
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun errorAddNewStudent(errorMessage: String) {
        showSnackbar(binding.root, "student not inserted successfully.").show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1500)
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
            val newStudent = Student(
                name = "$firstName $lastName",
                course = course,
                score = score.toInt()
            )
            presenter.onUpdateStudent(newStudent)
        } else {
            showSnackbar(binding.root, "Please enter complete information.").show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun successUpdateStudent() {
        showSnackbar(binding.root, "student updated successfully.").show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1500)
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun errorUpdateStudent(errorMessage: String) {
        showSnackbar(binding.root, "student not updated successfully.").show()
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 1500)
    }
}