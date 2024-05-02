package com.aliza.alizaandroid

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.ui.FragmentExoPlayer
import com.aliza.alizaandroid.ui.FragmentExoPlayerCustom
import com.aliza.alizaandroid.ui.FragmentVideoView

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding.btnFrgVideoView.setOnClickListener {
            transactionFragment(FragmentVideoView())
        }

        binding.btnFrgExoPlayer.setOnClickListener {
            transactionFragment(FragmentExoPlayer())
        }

        binding.btnFrgExoPlayerCustom.setOnClickListener {
            transactionFragment(FragmentExoPlayerCustom())
        }

    }
    private fun transactionFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.frame_container, fragment, null)
            .addToBackStack(null)
            .commit()
    }

}