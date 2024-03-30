package com.aliza.alizaandroid.DB

import androidx.room.*

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFood(food: Food)

//    @Insert
//    fun insertFood(food: Food)

    @Insert
    fun insertDefaultFood(foodList:List<Food>)

    @Delete
    fun deleteFood(food: Food)

//    @Update
//    fun updateFood(food: Food)

    @Query("SELECT * FROM tableFood WHERE txtSubject LIKE '%' || :searching || '%' ")
    fun searchFood(searching: String): List<Food>

    @Query("SELECT * FROM tableFood")
    fun getAllFood(): List<Food>

    @Query("DELETE FROM tableFood")
    fun deleteAllFood()
}

