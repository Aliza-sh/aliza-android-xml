package com.aliza.alizaandroid

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onCheckBoxClicked()
        onSwitchClicked()

        binding.btnFavorite.setOnClickListener {
            binding.checkTehran.isChecked = true
            binding.checkYazd.isChecked = true
        }

        binding.radiogroupMain.setOnCheckedChangeListener { _, i ->

            when (i) {
                R.id.radio_mashhad -> {
                    Toast.makeText(this, "Mashhad selected", Toast.LENGTH_SHORT).show()
                }
                R.id.radio_tehran -> {
                    Toast.makeText(this, "Tehran selected", Toast.LENGTH_SHORT).show()
                }
                R.id.radio_mazandaran -> {
                    Toast.makeText(this, "Mazandaran selected", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onSwitchClicked() {

        binding.switchWifi.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Wi-Fi connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Wi-Fi disconnected", Toast.LENGTH_SHORT).show()
            }
        }
        binding.switchBluetooth.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Bluetooth connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "bluetooth disconnected", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun onCheckBoxClicked() {

        binding.checkTehran.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Tehran selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tehran not selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.checkMashhad.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Mashhad selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Mashhad not selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.checkIsfahan.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Isfahan selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Isfahan not selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.checkYazd.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Yazd selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Yazd not selected", Toast.LENGTH_SHORT).show()
            }
        }

        binding.checkBoshehr.setOnCheckedChangeListener { _, b ->
            if (b) {
                Toast.makeText(this, "Boshehr selected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Boshehr not selected", Toast.LENGTH_SHORT).show()
            }
        }

    }
}