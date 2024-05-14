package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentRegisterBinding
import com.aliza.alizaandroid.utils.navOptions


class FragmentRegister : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signupBtn.setOnClickListener {
            findNavController().navigate(R.id.action_register_to_match,null, navOptions(true))
        }
    }
}
