package com.aliza.alizaandroid

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.ui.FragmentAnimationFromXml
import com.aliza.alizaandroid.ui.FragmentAnimationSet
import com.aliza.alizaandroid.ui.FragmentAnimationsLottie
import com.aliza.alizaandroid.ui.FragmentAnomationRecyclerview
import com.aliza.alizaandroid.ui.FragmentViewAnimations
import ir.dunijet.animation.ext.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding()=ActivityMainBinding.inflate(layoutInflater)
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnFrgViewAnimations.setOnClickListener {
            transactionFragment(FragmentViewAnimations())
        }

        binding.btnFrgAnimationSet.setOnClickListener {
            transactionFragment(FragmentAnimationSet())
        }

        binding.btnFrgAnimationXml.setOnClickListener {
            transactionFragment(FragmentAnimationFromXml())
        }

        binding.btnFrgAnimationsLottie.setOnClickListener {
            transactionFragment(FragmentAnimationsLottie())
        }

        binding.btnFrgAnimationRecyclerview.setOnClickListener {
            transactionFragment(FragmentAnomationRecyclerview())
        }
        binding.btnFrgOpenActivity.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun transactionFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .add(R.id.frame_container, fragment, null)
            .addToBackStack(null)
            .commit()
    }
}