package com.aliza.alizaandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.data.UserManager
import com.aliza.alizaandroid.data.UserModel
import com.aliza.alizaandroid.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding() = ActivityMainBinding.inflate(layoutInflater)

    private val userAdapter by lazy { UserAdapter() }
    private val userManager by lazy { UserManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
        userAdapter.differ.submitList(userManager.getUsers())
        Log.d("UserList", userManager.getUsers().toString())

        binding.addButton.setOnClickListener {
            if (userManager.addUser(
                    UserModel(
                        userManager.getMaxId() + 1,
                        "New User",
                        30
                    )
                )
            )
                userAdapter.differ.submitList(userManager.getUsers())
            Log.d("UserList", userManager.getUsers().toString())

        }

        binding.removeButton.setOnClickListener {
            if (userManager.removeUser(3))
                userAdapter.differ.submitList(userManager.getUsers())
            Log.d("UserList", userManager.getUsers().toString())

        }

        binding.updateButton.setOnClickListener {
            if (userManager.updateUser(UserModel(6, "Reza Updated", 28)))
                userAdapter.differ.submitList(userManager.getUsers())
            Log.d("UserList", userManager.getUsers().toString())

        }

        /*It is only for testing the adapter.

        userAdapter.differ.submitList(loadUserData())

        // Example Usage
        binding.addButton.setOnClickListener {
            val newUser = UserModel(12, "New User", 30)
            userAdapter.addUser(newUser)
        }

        binding.removeButton.setOnClickListener {
            userAdapter.removeUser(3)
        }

        binding.updateButton.setOnClickListener {
            val updatedUser = UserModel(2, "Reza Updated", 28)
            userAdapter.updateUser(updatedUser)
        }*/

    }
}