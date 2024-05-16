package com.aliza.alizaandroid.ui.student

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliza.alizaandroid.model.data.Student
import com.aliza.alizaandroid.model.repository.MainRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class StudentViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val progressBarSubject = BehaviorSubject.create<Boolean>()
    private lateinit var netDisposable: Disposable
    private val errorData = MutableLiveData<String>()

    fun getAllData(): LiveData<List<Student>> {
        return mainRepository.getAllStudents()
    }

    fun refreshData() {
        mainRepository
            .refreshData()
            .subscribeOn(Schedulers.io())
            .delay(2, TimeUnit.SECONDS)
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    netDisposable = d
                    progressBarSubject.onNext(true)
                }

                override fun onComplete() {
                    progressBarSubject.onNext(false)
                }
                override fun onError(e: Throwable) {
                    errorData.postValue(e.message ?: "unknown error!")
                }
            })
    }

    fun getErrorData(): LiveData<String> {
        return errorData
    }

    fun deleteStudent(studentName: String): Single<Int> {
        return mainRepository.deleteStudent(studentName)
    }

    override fun onCleared() {
        netDisposable.dispose()
        super.onCleared()
    }

}