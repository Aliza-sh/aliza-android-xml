package com.aliza.alizaandroid


import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.base.NetworkChecker
import com.aliza.alizaandroid.databinding.ActivityNetworkCheckerReceiversBinding

class NetworkCheckerReceiversActivity : BaseActivity<ActivityNetworkCheckerReceiversBinding>() {
    override fun inflateBinding() = ActivityNetworkCheckerReceiversBinding.inflate(layoutInflater)
    private lateinit var broadcastReceiver: BroadcastReceiver

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                checkNetworkConnection()
            }
        }

       val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
       registerReceiver(broadcastReceiver, intentFilter)

    }

    @SuppressLint("SetTextI18n")
    private fun checkNetworkConnection() {

        if (NetworkChecker(this).isInternetConnected) {
            binding.txtShowNetwork.text = "Internet Connected!"
        } else {
            binding.txtShowNetwork.text = "Internet Disconnected!"
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

}