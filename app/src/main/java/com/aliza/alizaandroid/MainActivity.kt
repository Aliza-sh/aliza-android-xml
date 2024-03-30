package com.aliza.alizaandroid

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.DB.Food
import com.aliza.alizaandroid.DB.FoodDao
import com.aliza.alizaandroid.DB.FoodDatabase
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.databinding.DialogAddNewItemBinding
import com.aliza.alizaandroid.databinding.DialogDeleteItemBinding
import com.aliza.alizaandroid.databinding.DialogUpdateItemBinding
import java.util.*
import kotlin.collections.ArrayList

const val TAG_MAIN_ACTIVIRY = "myActivity"
const val BASE_URL_IMAGE = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food"

class MainActivity : AppCompatActivity(), FoodAdapter.FoodEvents {
    lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: FoodAdapter
    lateinit var foodDao: FoodDao

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(TAG_MAIN_ACTIVIRY, "onCreate called")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDao = FoodDatabase.getDatabase(this).foodDao

        val sharedPreferences = getSharedPreferences("AlizaFood", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("firstRun", true)) {
            firstRun()
            sharedPreferences.edit().putBoolean("firstRun", false).apply()
        }

        showAllFood()
        binding.btnDClearAllFood.setOnClickListener {

            clearAllFood()
        }

        binding.btnAddNewFood.setOnClickListener {
            addNewFood()
        }

        binding.edtSearchMain.addTextChangedListener {
            search(it!!.toString())
        }

    }

    private fun firstRun() {

        val foodList =
            arrayListOf(
            Food(
                txtSubject = "Hamburger",
                txtDistance = "15",
                txtPrice = "3",
                txtLocation = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                numOfRating = 20,
                ratingBar = 4.5f
            ),
            Food(
                txtSubject = "Grilled fish",
                txtDistance = "20",
                txtPrice = "2.1",
                txtLocation = "Tehran, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                numOfRating = 10,
                ratingBar = 4f
            ),
            Food(
                txtSubject = "Lasania",
                txtDistance = "40",
                txtPrice = "1.4",
                txtLocation = "Isfahan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                numOfRating = 30,
                ratingBar = 2f
            ),
            Food(
                txtSubject = "pizza",
                txtDistance = "10",
                txtPrice = "2.5",
                txtLocation = "Zahedan, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                numOfRating = 80,
                ratingBar = 1.5f
            ),
            Food(
                txtSubject = "Sushi",
                txtDistance = "20",
                txtPrice = "3.2",
                txtLocation = "Mashhad, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                numOfRating = 200,
                ratingBar = 3f
            ),
            Food(
                txtSubject = "Roasted Fish",
                txtDistance = "40",
                txtPrice = "3.7",
                txtLocation = "Jolfa, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                numOfRating = 50,
                ratingBar = 3.5f
            ),
            Food(
                txtSubject = "Fried chicken",
                txtDistance = "70",
                txtPrice = "3.5",
                txtLocation = "NewYork, USA",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                numOfRating = 70,
                ratingBar = 2.5f
            ),
            Food(
                txtSubject = "Vegetable salad",
                txtDistance = "12",
                txtPrice = "3.6",
                txtLocation = "Berlin, Germany",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                numOfRating = 40,
                ratingBar = 4.5f
            ),
            Food(
                txtSubject = "Grilled chicken",
                txtDistance = "10",
                txtPrice = "3.7",
                txtLocation = "Beijing, China",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                numOfRating = 15,
                ratingBar = 5f
            ),
            Food(
                txtSubject = "Baryooni",
                txtDistance = "16",
                txtPrice = "10",
                txtLocation = "Ilam, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                numOfRating = 28,
                ratingBar = 4.5f
            ),
            Food(
                txtSubject = "Ghorme Sabzi",
                txtDistance = "11.5",
                txtPrice = "7.5",
                txtLocation = "Karaj, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                numOfRating = 27,
                ratingBar = 5f
            ),
            Food(
                txtSubject = "Rice",
                txtDistance = "12.5",
                txtPrice = "2.4",
                txtLocation = "Shiraz, Iran",
                urlImage = "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                numOfRating = 35,
                ratingBar = 2.5f
            ),
        )
        foodDao.insertDefaultFood(foodList)
    }

    private fun showAllFood() {

        val allFood = foodDao.getAllFood()
        myAdapter = FoodAdapter(ArrayList(allFood), this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        Log.v("testDatabase", allFood.toString())

    }
    private fun clearAllFood() {

        foodDao.deleteAllFood()
        showAllFood()

    }
    fun addNewFood() {


        Log.v(TAG_MAIN_ACTIVIRY, "add food clicked")

        val dialog = AlertDialog.Builder(this).create()
        val addDialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
        dialog.setView(addDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        addDialogBinding.dialogBtnDone.setOnClickListener {

            if (
                addDialogBinding.dialogEdtNamefood.length() > 0 &&
                addDialogBinding.dialogEdtLocation.length() > 0 &&
                addDialogBinding.dialogEdtPrice.length() > 0 &&
                addDialogBinding.dialogEdtDistance.length() > 0
            ) {
                val txtSubject = addDialogBinding.dialogEdtNamefood.text.toString()
                val txtLocation = addDialogBinding.dialogEdtLocation.text.toString()
                val txtDistance = addDialogBinding.dialogEdtDistance.text.toString()
                val txtPrice = addDialogBinding.dialogEdtPrice.text.toString()

                val numOfRating = (1 until 151).random() //or (1 .. 150)
                val ratingBar = 0f + Random().nextFloat() * (5f + 0f)
                val randomForUrl = (0..11).random()
                val urlPic = BASE_URL_IMAGE + randomForUrl.toString() + ".jpg"

                val newFood = Food(
                    txtSubject = txtSubject,
                    txtDistance = txtDistance,
                    txtPrice = txtPrice,
                    txtLocation = txtLocation,
                    urlImage = urlPic,
                    numOfRating = numOfRating,
                    ratingBar = ratingBar
                )
                myAdapter.addFood(newFood)
                foodDao.insertOrUpdateFood(newFood)

                dialog.dismiss()
                binding.recyclerMain.scrollToPosition(0)
                binding.recyclerMain.smoothScrollToPosition(0)

            } else {
                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    fun search(editText: String) {


        if (editText.isNotEmpty()) {
            val searchFood = foodDao.searchFood(editText)
            myAdapter.setData(ArrayList(searchFood))

        } else {
            val allFood = foodDao.getAllFood()
            myAdapter.setData(ArrayList(allFood))
        }
    }

    override fun onFoodClicked(food: Food, position: Int) {
        Log.v(TAG_MAIN_ACTIVIRY, "clicked on ${food.txtSubject}")

        val dialog = AlertDialog.Builder(this).create()
        val updateDialogBinding = DialogUpdateItemBinding.inflate(layoutInflater)
        dialog.setView(updateDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        updateDialogBinding.dialogEdtNamefood.setText(food.txtSubject)
        updateDialogBinding.dialogEdtLocation.setText(food.txtLocation)
        updateDialogBinding.dialogEdtPrice.setText(food.txtPrice)
        updateDialogBinding.dialogEdtDistance.setText(food.txtDistance)

        updateDialogBinding.dialogBtnUpdateCansel.setOnClickListener {
            dialog.dismiss()
        }
        updateDialogBinding.dialogBtnUpdateSure.setOnClickListener {

            if (
                updateDialogBinding.dialogEdtNamefood.length() > 0 &&
                updateDialogBinding.dialogEdtLocation.length() > 0 &&
                updateDialogBinding.dialogEdtPrice.length() > 0 &&
                updateDialogBinding.dialogEdtDistance.length() > 0
            ) {
                val txtSubject = updateDialogBinding.dialogEdtNamefood.text.toString()
                val txtLocation = updateDialogBinding.dialogEdtLocation.text.toString()
                val txtDistance = updateDialogBinding.dialogEdtDistance.text.toString()
                val txtPrice = updateDialogBinding.dialogEdtPrice.text.toString()

                val newFood = Food(
                    id = food.id,
                    txtSubject = txtSubject,
                    txtDistance = txtDistance,
                    txtPrice = txtPrice,
                    txtLocation = txtLocation,
                    urlImage = food.urlImage,
                    numOfRating = food.numOfRating,
                    ratingBar = food.ratingBar
                )
                myAdapter.updateFood(newFood, position)
                foodDao.insertOrUpdateFood(newFood)

                dialog.dismiss()
            } else {
                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید یره :)", Toast.LENGTH_SHORT)
            }
        }

    }
    override fun onFoodLongClicked(food: Food, position: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val deleteDialogBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(deleteDialogBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        deleteDialogBinding.dialogBtnDeleteCansel.setOnClickListener {
            dialog.dismiss()
        }
        deleteDialogBinding.dialogBtnDeleteSure.setOnClickListener {
            dialog.dismiss()
            myAdapter.removeFood(food, position)
            foodDao.deleteFood(food)
        }
    }
}