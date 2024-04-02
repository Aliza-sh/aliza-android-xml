package com.aliza.alizaandroid.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.R
import com.aliza.alizaandroid.databinding.ItemFoodBinding
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvents: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    lateinit var binding: ItemFoodBinding

    inner class FoodViewHolder(binding: ItemFoodBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            binding.itemTxtSubject.text = data[position].txtSubject
            binding.itemTxtLocathion.text = data[position].txtLocation
            binding.itemTxtPrice.text = "$ " + data[position].txtPrice
            binding.itemTxtDistance.text = data[position].txtDistance + " miles from you"
            binding.itemRatingMain.rating = data[position].ratingBar
            binding.itemTxtRating.text = "( " + data[position].numOfRating.toString() + " Ratings )"
            Glide
                .with(binding.root.context)
                .load(data[position].urlImage)
                .transform(RoundedCornersTransformation(16, 4))
                .into(binding.itemImgMain)


            itemView.setOnClickListener {

                foodEvents.onFoodClicked(data[position], position)

                Toast.makeText(
                    binding.root.context,
                    "clicked on ${binding.itemTxtSubject.text}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            itemView.setOnLongClickListener {

                foodEvents.onFoodLongClicked(data[adapterPosition], adapterPosition)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        Log.v("testApp", "onCreateViewHolder Called")
        binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        Log.v("testApp", "onBindViewHolder Called")
        holder.itemView.startAnimation(AnimationUtils.loadAnimation(binding.root.context,
            R.anim.anim_recycler_item
        ))
        holder.bindData(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addFood(newFood: Food) {
        data.add(0, newFood)
        notifyItemInserted(0)
    }
    fun removeFood(oldFood: Food, oldPosition: Int) {
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)
    }
    fun updateFood(newFood: Food, position: Int) {

        data.set(position, newFood)
        notifyItemChanged(position)

    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: ArrayList<Food>) {

        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
    interface FoodEvents {
        fun onFoodClicked(food: Food, position: Int)
        fun onFoodLongClicked(food: Food, position: Int)
    }
}