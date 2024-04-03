package com.aliza.alizaandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.ui.FragmentAnimationFromXml
import com.aliza.alizaandroid.ui.FragmentAnimationSet
import com.aliza.alizaandroid.ui.FragmentAnimationsLottie
import com.aliza.alizaandroid.ui.FragmentAnomationRecyclerview
import com.aliza.alizaandroid.ui.FragmentViewAnimations

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFrgViewAnimations.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.frame_container, FragmentViewAnimations(), null)
                .addToBackStack(null)
                .commit()
        }

        binding.btnFrgAnimationSet.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.frame_container, FragmentAnimationSet(), null)
                .addToBackStack(null)
                .commit()
        }

        binding.btnFrgAnimationXml.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.frame_container, FragmentAnimationFromXml(),null)
                .addToBackStack(null)
                .commit()
        }

        binding.btnFrgAnimationsLottie.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.frame_container, FragmentAnimationsLottie(),null)
                .addToBackStack(null)
                .commit()
        }

        binding.btnFrgAnimationRecyclerview.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction
                .add(R.id.frame_container, FragmentAnomationRecyclerview(),null)
                .addToBackStack(null)
                .commit()
        }

        binding.btnFrgOpenActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }
}