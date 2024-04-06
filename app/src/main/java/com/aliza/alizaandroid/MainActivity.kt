package com.aliza.alizaandroid

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import ir.aliza.exploreinstagram.NetworkChecker

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (NetworkChecker(this).isInternetConnected) {
            binding.btnStartDownload.setOnClickListener {
                //Method 1 =>
                val thread = DownloadThread(this, binding)
                thread.start()
            }
        } else {
            val snackbar =
                Snackbar.make(binding.root, "اینترنت خود را متصل کنید!", Snackbar.LENGTH_LONG)
            snackbar.view.layoutDirection = View.LAYOUT_DIRECTION_RTL
            snackbar.show()
        }

        /*Method 2 =>
        Thread(Thread2(applicationContext)).start()*/

        /*Method 3 =>
        Thread(object : Runnable {
            override fun run() {
                Log.v("threadAliza", Thread.currentThread().name)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    Toast.makeText(applicationContext, Thread.currentThread().name, Toast.LENGTH_SHORT).show()
                }
            }

        }).start()*/

        /*Method 4 =>
        Thread {
            Log.v("threadAliza", Thread.currentThread().name)
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                Toast.makeText(applicationContext, Thread.currentThread().name, Toast.LENGTH_SHORT).show()
            }
        }.start()*/

    }
}

/*Method 2 =>
class Thread2(val context: Context) : Runnable {
    override fun run() {
        Log.v("threadAliza", Thread.currentThread().name)
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(context, Thread.currentThread().name, Toast.LENGTH_SHORT).show()
        }
    }
}*/
