package com.aliza.alizaandroid.ui.student

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.utils.EXTRA_STUDENT
import com.aliza.alizaandroid.ui.addStudent.AddStudentActivity
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.NetworkChecker
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityStudentBinding
import com.aliza.alizaandroid.model.net.ApiManager
import com.aliza.alizaandroid.model.data.Student
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class StudentActivity : BaseActivity<ActivityStudentBinding>(), StudentAdapter.StudentEvent {
    override fun inflateBinding(): ActivityStudentBinding =
        ActivityStudentBinding.inflate(layoutInflater)

    private val apiManager = ApiManager()
    private lateinit var myAdapter: StudentAdapter

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        binding.btnAddStudent.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
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
            getDataFromApi()
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

    private fun getDataFromApi() {
        apiManager.getAllStudents(object : ApiManager.ApiCallback<List<Student>> {
            override fun onSuccess(data: List<Student>) {
                setDataToRecycler(data)
            }

            override fun onError(errorMessage: String) {
                Log.v("testApi", errorMessage)
            }
        })
    }

    fun setDataToRecycler(data: List<Student>) {
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
        val intent = Intent(this, AddStudentActivity::class.java)
        intent.putExtra(EXTRA_STUDENT, student)
        startActivity(intent)
    }

    override fun onItemLongClicked(student: Student, position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Delete this Item?")
            .setPositiveButton("confirm") { dialog, which ->
                deleteDataFromServer(student, position)
                dialog.dismiss()
            }
            .setNegativeButton("cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteDataFromServer(student: Student, position: Int) {
        apiManager.deleteStudent(student.name, object : ApiManager.ApiCallback<Int> {
            override fun onSuccess(data: Int) {
                myAdapter.removeItem(student, position)
            }
            override fun onError(errorMessage: String) {
                Log.v("testApi", errorMessage)
            }
        })
    }

}