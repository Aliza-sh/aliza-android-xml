package com.aliza.alizaandroid.ui.rxjava

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityRxjavaBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxjavaActivity : BaseActivity<ActivityRxjavaBinding>() {

    override fun inflateBinding(): ActivityRxjavaBinding =
        ActivityRxjavaBinding.inflate(layoutInflater)

    lateinit var disposable: Disposable

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarAddStudentActivity)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        SimpleDefinition()
        customObservable()




    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }

    private fun SimpleDefinition(){
        Observable
            .just("Hi", "Bitcoin is Good", "Ethereum is Bad", "I love Space")
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: String) {
                    Log.v("testLog" , t)
                }

                override fun onError(e: Throwable) {
                    Log.v("testLog" , e.message!!)
                }

                override fun onComplete() {
                    Log.v("testLog" , "data finished")
                }

            })
    }

    private fun customObservable(){
        getDataOneByOne()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                it > 5
            }
            .map {
                "number is $it"
            }
            .take(3)
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: String) {
                    Log.v("customObservable", t)
                }

                override fun onError(e: Throwable) {
                    Log.v("customObservable", e.message!!)
                }

                override fun onComplete() {
                    Log.v("customObservable", "data finished")
                }

            })

        getAllData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Int>> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(t: List<Int>) {
                    repeat(t.size) {
                        Log.v("customObservable", t.toString())
                    }
                }

                override fun onError(e: Throwable) {
                    Log.v("customObservable", e.message!!)
                }

            })
    }

    private fun getAllData(): Single<List<Int>> {
        return Single.create { emitter ->
            // data is ready
            emitter.onSuccess(listOf(1, 4, 5))
        }
    }

    private fun getDataOneByOne(): Observable<Int> {
        val data = listOf(1, 4, 5, 8, 7, 14, 15, 16, 20)
        return Observable.create { emitter ->
            data.forEach {
                emitter.onNext(it)
            }
        }
    }


}