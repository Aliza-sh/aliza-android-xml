package com.aliza.alizaandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aliza.alizaandroid.R
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {

    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw IllegalArgumentException("binding cannot be null")
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigate(actionId: Int, useCustomAnimation: Boolean = false,popUpToId: Int = -1,popUpToInclusive: Boolean = false) {

        val navOptions = if (useCustomAnimation) {
            NavOptions.Builder()
                .setEnterAnim(R.anim.anim_slide_from_right)
                .setExitAnim(R.anim.anim_slide_to_left)
                .setPopEnterAnim(R.anim.anim_slide_from_left)
                .setPopExitAnim(R.anim.anim_slide_to_right)
                .setPopUpTo(popUpToId,popUpToInclusive)
                .build()
        } else {
            NavOptions.Builder()
                .build()
        }

        findNavController().navigate(actionId, null, navOptions)
    }

    //Method to get the name of the caller's class.
    protected open fun getCallerClassName(): String {
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
    //Implement the following method in the caller class to return the name.
    /*
    override fun getCallerClassName(): String {
        return "CallerActivity"

    }
    */

}