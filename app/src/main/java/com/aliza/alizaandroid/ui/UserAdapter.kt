package com.aliza.alizaandroid.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.data.UserModel
import com.aliza.alizaandroid.databinding.ItemUserBinding
import com.aliza.alizaandroid.utils.BaseDiffUtils

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var items = emptyList<UserModel>()

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindViews(item: UserModel) {
            binding.itemUserInfo.text = "${item.name} ${item.age}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) =
        holder.bindViews(items[position])


    override fun getItemCount(): Int = items.size

    fun setData(data: List<UserModel>) {
        val adapterDiffUtils = BaseDiffUtils(items, data)
        val diffUtils = DiffUtil.calculateDiff(adapterDiffUtils)
        items = data
        diffUtils.dispatchUpdatesTo(this)
    }

    /*It is only for testing delete, change, and add operations in the adapter.

    fun addUser(user: UserModel) {
        val newList = ArrayList(differ.currentList)
        newList.add(user)
        setData(newList)
    }

    fun removeUser(userId: Int) {
        val newList = differ.currentList.filter { it.id != userId }
        setData(newList)
    }

    fun updateUser(updatedUser: UserModel) {
        val newList = differ.currentList.map {
            if (it.id == updatedUser.id) updatedUser else it
        }
        setData(newList)
    }*/
}