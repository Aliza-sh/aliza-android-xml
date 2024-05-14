package com.aliza.alizaandroid.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.ListViewItemBinding

class LeaderboardAdapter(
    private val myDataset: Array<String>,
    private val leaderboardEvents: LeaderboardEvents
) :
    RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>() {

    lateinit var binding: ListViewItemBinding

    inner class ViewHolder(val item: View) : RecyclerView.ViewHolder(item) {

        fun bind(item: View) {
            binding.userNameText.text = myDataset[position]

            binding.userAvatarImage.setImageResource(listOfAvatars[position])

            item.setOnClickListener {
                leaderboardEvents.onItemClicked(myDataset[adapterPosition])
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        binding = ListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(holder.item)

    }

    override fun getItemCount() = myDataset.size
    interface LeaderboardEvents {
        fun onItemClicked(name: String)
    }

}

private val listOfAvatars = listOf(
    R.drawable.avatar_1_raster,
    R.drawable.avatar_2_raster,
    R.drawable.avatar_3_raster,
    R.drawable.avatar_4_raster,
    R.drawable.avatar_5_raster,
    R.drawable.avatar_6_raster,
    R.drawable.avatar_7_raster,
    R.drawable.avatar_8_raster,
    R.drawable.avatar_9_raster,
    R.drawable.avatar_10_raster,
    R.drawable.avatar_11_raster,
    R.drawable.avatar_12_raster,
    R.drawable.avatar_13_raster,
    R.drawable.avatar_14_raster,
    R.drawable.avatar_15_raster,
    R.drawable.avatar_16_raster,
)