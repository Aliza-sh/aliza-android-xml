package com.aliza.alizaandroid

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.DB.Food
import com.aliza.alizaandroid.databinding.ItemFoodBinding
import com.bumptech.glide.Glide
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvents: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    lateinit var binding: ItemFoodBinding

    /*
    inner class FoodViewHolder(itemView: View, private val context: Context) :
         RecyclerView.ViewHolder(itemView)
         */
    inner class FoodViewHolder(binding: ItemFoodBinding):
        RecyclerView.ViewHolder(binding.root) {

        /*
        val imgMain = itemView.findViewById<ImageView>(R.id.item_img_main)
        val txtSubject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        val txtLocation = itemView.findViewById<TextView>(R.id.item_txt_locathion)
        val txtPrice = itemView.findViewById<TextView>(R.id.item_txt_price)
        val txtDistance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.item_rating_main)
        val txtRating = itemView.findViewById<TextView>(R.id.item_txt_rating)
        */

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {

            /*
            txtSubject.text = data[position].txtSubject
            txtLocation.text = data[position].txtLocation
            txtPrice.text = "$ " + data[position].txtPrice
            txtDistance.text = data[position].txtDistance + " miles from you"
            ratingBar.rating = data[position].ratingBar
            txtRating.text = "( " + data[position].numOfRating.toString() + " Ratings )"
            Glide
                .with(context)
                .load(data[position].urlImage)
                .transform(RoundedCornersTransformation(16, 4))
                .into(imgMain)
            */

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

                //Toast.makeText(context, "clicked on ${txtSubject.text}", Toast.LENGTH_SHORT).show()
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
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        // return FoodViewHolder(view, parent.context)
        binding = ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        Log.v("testApp", "onBindViewHolder Called")
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
