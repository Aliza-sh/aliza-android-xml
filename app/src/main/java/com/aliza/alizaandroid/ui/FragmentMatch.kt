package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentMatchBinding

class FragmentMatch : BaseFragment<FragmentMatchBinding>(FragmentMatchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn3.setOnClickListener {
            navigate(R.id.action_match_to_inGame,true)
        }
    }

}
