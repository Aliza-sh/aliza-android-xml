package com.aliza.alizaandroid.ui.student

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.utils.EXTRA_STUDENT
import com.aliza.alizaandroid.ui.addOrUpdateStudent.AddOrUpdateStudentActivity
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.NetworkChecker
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityStudentBinding
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.StudentRepository
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StudentActivity : BaseActivity<ActivityStudentBinding>(), StudentAdapter.StudentEvent, StudentContract.View {
    override fun inflateBinding(): ActivityStudentBinding =
        ActivityStudentBinding.inflate(layoutInflater)

    private lateinit var presenter: StudentContract.Presenter
    private lateinit var myAdapter: StudentAdapter

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbarMain)

        presenter = StudentPresenter(StudentRepository())

        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, AddOrUpdateStudentActivity::class.java)
            startActivity(intent)
        }

        binding.swipeRefreshMain.setOnRefreshListener {
            networkChecker()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.swipeRefreshMain.isRefreshing = false
            }, 1500)

        }

    }

    private fun networkChecker() {
        if (NetworkChecker(applicationContext).isInternetConnected) {
            presenter.onAttach(this)
        } else {
            showSnackbar(binding.root,"No Internet!")
                .setAction("Retry") {
                    networkChecker()
                }
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        networkChecker()
    }
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }


    override fun showStudent(data: List<Student>) {
        setDataToRecycler(data)
    }

    private fun setDataToRecycler(data: List<Student>) {
        val myData = ArrayList(data)
        myAdapter = StudentAdapter(myData, this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onItemClicked(student: Student, position: Int) {
        updateDataInServer(student)
    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun updateDataInServer(student: Student) {
        val intent = Intent(this, AddOrUpdateStudentActivity::class.java)
        intent.putExtra(EXTRA_STUDENT, student)
        startActivity(intent)
    }

    override fun onItemLongClicked(student: Student, position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete this Item?")
            .setPositiveButton("confirm") { dialog, which ->
                presenter.onDeleteStudent(student,position)
                dialog.dismiss()
            }
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
    override fun deleteStudent(oldStudent: Student, pos: Int) {
        myAdapter.removeItem(oldStudent, pos)
    }
    override fun errorDeleteStudent(errorMessage: String) {
        showSnackbar(binding.root, "student not Deleted successfully.").show()
    }

}