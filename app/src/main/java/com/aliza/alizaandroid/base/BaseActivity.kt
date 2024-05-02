package com.aliza.alizaandroid.base

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.aliza.alizaandroid.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB
    abstract fun inflateBinding(): VB

    private var onBackPressedCallback: OnBackPressedCallback? = null

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateBinding()
        setContentView(binding.root)
        overridePendingTransitionEnter()

        onBackPressedCallback = object : OnBackPressedCallback(true) {
            @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
            override fun handleOnBackPressed() {
                // Check if there are any fragments in the back stack
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                } else if (supportFragmentManager.backStackEntryCount > 0) {
                    // Pop the fragment from the back stack
                    onBack()
                    supportFragmentManager.popBackStack()
                } else {
                    // Close the activity
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, onBackPressedCallback!!)
    }
    open fun onBack(){}

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback?.remove()
    }

    //Method to get the name of the caller's class.
    protected fun getCallerClassName(): String {
        val stackTrace = Thread.currentThread().stackTrace
        var callerClassName = ""
        for (stackTraceElement in stackTrace) {
            if (stackTraceElement.className != this.javaClass.name) {
                callerClassName = stackTraceElement.className
                break
            }
        }
        return callerClassName
    }
    // Implement the following method in the caller class to return the name.
    /*
    override fun getCallerClassName(): String {
        return "Activity"
    }
    */

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun overridePendingTransitionEnter() {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                R.anim.anim_slide_from_right,
                R.anim.anim_slide_to_left

            )
        } else {
            overridePendingTransition(
                R.anim.anim_slide_from_right,
                R.anim.anim_slide_to_left
            )
        }

    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun overridePendingTransitionExit() {
        if (Build.VERSION.SDK_INT >= 34) {

            overrideActivityTransition(
                OVERRIDE_TRANSITION_CLOSE,
                R.anim.anim_slide_from_left,
                R.anim.anim_slide_to_right
            )
        } else {
            overridePendingTransition(
                R.anim.anim_slide_from_left,
                R.anim.anim_slide_to_right
            )
        }
    }

}