package com.aliza.alizaandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listUrl = listOf(
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img1.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img2.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img3.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img4.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img5.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img6.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img7.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img8.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img9.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img10.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img11.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img12.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img13.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img14.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img15.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img16.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img17.png",
            "https://dunijet.ir/YaghootAndroidFiles/ExplorePics/img18.png"
        )

        if( NetworkChecker(this).isInternetConnected ){

            val glide = Glide.with(this)

            glide
                .load(listUrl[0])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img1)
            glide
                .load(listUrl[1])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img2)
            glide
                .load(listUrl[2])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img3)
            glide
                .load(listUrl[3])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img4)
            glide
                .load(listUrl[4])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img5)
            glide
                .load(listUrl[5])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img6)
            glide
                .load(listUrl[6])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img7)
            glide
                .load(listUrl[7])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img8)
            glide
                .load(listUrl[8])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img9)
            glide
                .load(listUrl[9])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img10)
            glide
                .load(listUrl[10])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img11)
            glide
                .load(listUrl[11])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img12)
            glide
                .load(listUrl[12])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img13)
            glide
                .load(listUrl[13])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img14)
            glide
                .load(listUrl[14])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img15)
            glide
                .load(listUrl[15])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img16)
            glide
                .load(listUrl[16])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img17)
            glide
                .load(listUrl[17])
                .error(R.drawable.img_errorimg)
                .placeholder(R.drawable.img_load)
                .into(binding.img18)

        }else{
            Toast.makeText(this, "اینترنت خود را متصل کنید.", Toast.LENGTH_SHORT).show()
        }
    }
}
/*
val internet: Boolean = NetworkChecker(this).isInternetConnected
Toast.makeText(this, internet.toString(), Toast.LENGTH_SHORT).show()
*/