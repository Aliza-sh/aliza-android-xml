package com.aliza.alizaandroid

import android.os.Bundle
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import android.content.Context
import android.content.SharedPreferences

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences(SHAREDPREFERENCES_PERSON_FORM, Context.MODE_PRIVATE)

        // put data into shared preferences =>
        binding.btnSubmit.setOnClickListener {

            //way one =>
            val name = binding.edtName.text.toString()
            sharedPreferences.edit().putString(PERSON_NAME, name).apply()

            val email = binding.edtEmail.text.toString()
            sharedPreferences.edit().putString(PERSON_EMAIL, email).apply()

            if (binding.radioMale.isChecked) {
                // user is male =>
                sharedPreferences.edit().putBoolean(PERSON_ISMALE, true).apply()
            } else {
                // user is female =>
                sharedPreferences.edit().putBoolean(PERSON_ISMALE, false).apply()
            }

            /* way two =>
            val editor = sharedPreferences.edit()
            editor.putString(PERSON_NAME, name)
            editor.putString(PERSON_EMAIL, email)
            if (binding.radioMale.isChecked) {
                // user is male =>
                editor.putBoolean(PERSON_ISMALE, true)
            } else {
                // user is female =>
                editor.putBoolean(PERSON_ISMALE, false)
            }
            editor.apply()*/
        }

        // get from shared preferences =>
        val name = sharedPreferences.getString(PERSON_NAME, "")
        val email = sharedPreferences.getString(PERSON_EMAIL, "")
        val isMale = sharedPreferences.getBoolean(PERSON_ISMALE, true)

        binding.edtName.setText(name)
        binding.edtEmail.setText(email)

        if (isMale) {
            // male =>
            binding.radioMale.isChecked = true
        } else {
            // female =>
            binding.radioFemale.isChecked = true
        }

    }

    private fun functionsOfSharedPreferences() {
        val isNameAvailable = sharedPreferences.contains(PERSON_NAME) // check exist item
        sharedPreferences.edit().remove(PERSON_EMAIL).apply() // remove an item
        sharedPreferences.edit().clear().apply() // remove xml file
    }
}
