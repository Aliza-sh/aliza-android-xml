package com.aliza.alizaandroid

import android.Manifest.permission.POST_NOTIFICATIONS
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startBackgroundService()

        requestNotificationPermission {
            startForegroundService()
        }

    }

    private fun startBackgroundService() {
        val intent = Intent(this , BackgroundService::class.java)
        startService(intent)
    }
    private fun startForegroundService() {
        val intent = Intent(this , ForegroundService::class.java)
        startService(intent)
    }

    private fun requestNotificationPermission(onPermissionGranted: () -> Unit) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(POST_NOTIFICATIONS),
                    1
                )
            } else {
                onPermissionGranted()
            }
        } else {
            //Handle notification permission request for pre-Android 13 versions (if needed)
        }
    }


}