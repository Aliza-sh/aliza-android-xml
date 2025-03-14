package com.aliza.alizaandroid.ui.BottomNavigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityBottomNavigationBinding

    //Other
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            navController = findNavController(R.id.pages3NavHost)
            bottomNav.setupWithNavController(navController)
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }
}