package com.aliza.alizaandroid.ui.pages2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.ActivityPages2Binding

class Pages2Activity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityPages2Binding

    //Other
    private lateinit var navController: NavController
    //private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPages2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            //To use it, you need to use "fragment" in XML.
            navController = findNavController(R.id.pages2NavHost)
            /*
                appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.detailFragment))
                setupActionBarWithNavController(navController, appBarConfiguration)
            */
        }
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

}

