package com.aliza.alizaandroid

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aliza.alizaandroid.DB.Food
import com.aliza.alizaandroid.adapter.FoodAdapter
import com.aliza.alizaandroid.base.BaseActivity
import com.aliza.alizaandroid.databinding.ActivityMainBinding
import com.aliza.alizaandroid.databinding.DialogAddNewItemBinding
import com.aliza.alizaandroid.databinding.DialogDeleteItemBinding
import com.aliza.alizaandroid.databinding.DialogUpdateItemBinding

class MainActivityButRoom : BaseActivity<ActivityMainBinding>(), FoodAdapter.FoodEvents {
    lateinit var myAdapter: FoodAdapter
    lateinit var foodList: ArrayList<Food>
    override fun inflateBinding(): ActivityMainBinding =ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // how to use recycler view :
        // 1. create view of recyclerView in activity_main.xml
        // 2. create item for recyclerView
        // 3. create adapter and view holder for recyclerView
        // 4. set adapter to recyclerView and set layout manager

        foodList = arrayListOf(
            Food(
                0,
                "Hamburger",
                "15",
                "3",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                20,
                4.5f
            ),
            Food(
                0,
                "Grilled fish",
                "20",
                "2.1",
                "Tehran, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                10,
                4f
            ),
            Food(
                0,
                "Lasania",
                "40",
                "1.4",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                30,
                2f
            ),
            Food(
                0,
                "pizza",
                "10",
                "2.5",
                "Zahedan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                80,
                1.5f
            ),
            Food(
                0,
                "Sushi",
                "20",
                "3.2",
                "Mashhad, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                200,
                3f
            ),
            Food(
                0,
                "Roasted Fish",
                "40",
                "3.7",
                "Jolfa, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                50,
                3.5f
            ),
            Food(
                0,
                "Fried chicken",
                "70",
                "3.5",
                "NewYork, USA",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                70,
                2.5f
            ),
            Food(
                0,
                "Vegetable salad",
                "12",
                "3.6",
                "Berlin, Germany",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                40,
                4.5f
            ),
            Food(
                0,
                "Grilled chicken",
                "10",
                "3.7",
                "Beijing, China",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                15,
                5f
            ),
            Food(
                0,
                "Baryooni",
                "16",
                "10",
                "Ilam, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                28,
                4.5f
            ),
            Food(
                0,
                "Ghorme Sabzi",
                "11.5",
                "7.5",
                "Karaj, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                27,
                5f
            ),
            Food(
                0,
                "Rice",
                "12.5",
                "2.4",
                "Shiraz, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                35,
                2.5f
            ),
        )

        myAdapter = FoodAdapter(foodList.clone() as ArrayList<Food>, this)
        binding.recyclerMain.adapter = myAdapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        binding.btnAddNewFood.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()

            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)
            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.dialogBtnDone.setOnClickListener {

                if (
                    dialogBinding.dialogEdtNamefood.length() > 0 &&
                    dialogBinding.dialogEdtLocation.length() > 0 &&
                    dialogBinding.dialogEdtPrice.length() > 0 &&
                    dialogBinding.dialogEdtDistance.length() > 0
                ) {

                    val txtName = dialogBinding.dialogEdtNamefood.text.toString()
                    val txtPrice = dialogBinding.dialogEdtPrice.text.toString()
                    val txtDistance = dialogBinding.dialogEdtDistance.text.toString()
                    val txtCity = dialogBinding.dialogEdtLocation.text.toString()
                    val txtRatingNumber: Int = (1..150).random()
                    val ratingBarStar: Float = (1..5).random().toFloat()

                    val randomForUrl = (0 until 12).random()
                    val urlPic = foodList[randomForUrl].urlImage

                    val newFood = Food(
                        0,
                        txtName,
                        txtPrice,
                        txtDistance,
                        txtCity,
                        urlPic,
                        txtRatingNumber,
                        ratingBarStar
                    )
                    myAdapter.addFood(newFood)

                    dialog.dismiss()
                    binding.recyclerMain.scrollToPosition(0)


                } else {

                    Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT)
                        .show()

                }

            }

        }

        binding.edtSearchMain.addTextChangedListener { editTextInput ->

            if (editTextInput!!.isNotEmpty()) {

                // filter data   'h'
                val cloneList = foodList.clone() as ArrayList<Food>
                val filteredList = cloneList.filter { foodItem ->
                    foodItem.txtSubject.contains(editTextInput)
                }

                myAdapter.setData(ArrayList(filteredList))


            } else {

                // show all data :
                myAdapter.setData(foodList.clone() as ArrayList<Food>)

            }
        }
    }

    override fun onFoodClicked(food: Food, position: Int) {

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

                val txtName = updateDialogBinding.dialogEdtNamefood.text.toString()
                val txtPrice = updateDialogBinding.dialogEdtPrice.text.toString()
                val txtDistance = updateDialogBinding.dialogEdtDistance.text.toString()
                val txtCity = updateDialogBinding.dialogEdtLocation.text.toString()

                // create new food to add to recycler view
                val newFood = Food(
                    0,
                    txtName,
                    txtPrice,
                    txtDistance,
                    txtCity,
                    food.urlImage,
                    food.numOfRating,
                    food.ratingBar
                )

                // update item :
                myAdapter.updateFood(newFood, position)

                dialog.dismiss()

            } else {
                Toast.makeText(this, "لطفا همه مقادیر را وارد کن :)", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onFoodLongClicked(food: Food, pos: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.dialogBtnDeleteCansel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.dialogBtnDeleteSure.setOnClickListener {

            dialog.dismiss()
            myAdapter.removeFood(food, pos)
            foodList.remove(food)

        }
    }
}