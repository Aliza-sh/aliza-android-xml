package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.base.BaseFragment
import com.aliza.alizaandroid.databinding.FragmentMatchBinding
import com.aliza.alizaandroid.utils.navOptions

class FragmentMatch : BaseFragment<FragmentMatchBinding>(FragmentMatchBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.playBtn3.setOnClickListener {
            findNavController().navigate(R.id.action_match_to_inGame,null, navOptions(true))
        }
    }

}
