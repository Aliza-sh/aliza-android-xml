package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentRegisterBinding


class FragmentRegister : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.signupBtn.setOnClickListener {
            navigate(R.id.action_register_to_match,true)
        }
    }
}
