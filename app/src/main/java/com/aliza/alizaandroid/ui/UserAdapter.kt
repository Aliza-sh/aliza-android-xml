package com.aliza.alizaandroid.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.data.UserModel
import com.aliza.alizaandroid.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindViews(item: UserModel) {
            binding.itemUserInfo.text = "${item.name} ${item.age}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bindViews(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    /*It is only for testing delete, change, and add operations in the adapter.

    fun addUser(user: UserModel) {
        val newList = ArrayList(differ.currentList)
        newList.add(user)
        differ.submitList(newList)
    }

    fun removeUser(userId: Int) {
        val newList = differ.currentList.filter { it.id != userId }
        differ.submitList(newList)
    }

    fun updateUser(updatedUser: UserModel) {
        val newList = differ.currentList.map {
            if (it.id == updatedUser.id) updatedUser else it
        }
        differ.submitList(newList)
    }*/
}