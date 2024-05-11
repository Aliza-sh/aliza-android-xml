package com.aliza.alizaandroid.ui.rxjava

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityRxjavaBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable

class RxjavaActivity : BaseActivity<ActivityRxjavaBinding>() {

    override fun inflateBinding(): ActivityRxjavaBinding  = ActivityRxjavaBinding.inflate(layoutInflater)

    lateinit var disposable: Disposable

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbarAddStudentActivity)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

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

}