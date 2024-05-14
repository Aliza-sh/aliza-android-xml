package com.aliza.alizaandroid.ui.student

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.EXTRA_STUDENT
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.utils.NetworkChecker
import com.aliza.alizaandroid.utils.showSnackbar
import com.aliza.alizaandroid.databinding.ActivityStudentBinding
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.MainRepository
import com.aliza.alizaandroid.ui.addOrUpdateStudent.AddOrUpdateStudentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class StudentActivity : BaseActivity<ActivityStudentBinding>(), StudentAdapter.StudentEvent {
    override fun inflateBinding(): ActivityStudentBinding =
        ActivityStudentBinding.inflate(layoutInflater)

    private lateinit var myAdapter: StudentAdapter

    private lateinit var studentViewModel:StudentViewModel
    private val compositeDisposable = CompositeDisposable()

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarMain)

        studentViewModel = StudentViewModel(MainRepository())

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

        compositeDisposable.add(
            studentViewModel.progressBarSubject.subscribe {
                if (it) {
                    runOnUiThread {
                        binding.progressMain.visibility = VISIBLE
                        binding.recyclerMain.visibility = INVISIBLE
                    }
                } else {
                    runOnUiThread {
                        binding.progressMain.visibility = INVISIBLE
                        binding.recyclerMain.visibility = VISIBLE
                    }
                }
            }
        )
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun getDataFromApi() {
        studentViewModel
            .getAllStudents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :SingleObserver<List<Student>>{
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
                override fun onError(e: Throwable) {
                    Log.e("testApi", e.message ?: "null")
                }
                override fun onSuccess(t: List<Student>) {
                    setDataToRecycler(t)
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
        val intent = Intent(this, AddOrUpdateStudentActivity::class.java)
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
        studentViewModel
            .deleteStudent(student.name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Int> {
                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }
                override fun onError(e: Throwable) {
                    Log.e("testApi", e.message ?: "null")
                }
                override fun onSuccess(t: Int) {
                    myAdapter.removeItem(student, position)
                }
            })
    }
}