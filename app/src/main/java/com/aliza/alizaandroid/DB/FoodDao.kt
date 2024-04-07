package com.aliza.alizaandroid.DB

import androidx.room.*
import com.aliza.alizaandroid.base.BaseDao

@Dao
interface FoodDao :BaseDao<Food>{

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateFood(food: Food)*/

    @Insert
    fun insertDefaultFood(foodList:List<Food>)

    @Query("SELECT * FROM tableFood WHERE txtSubject LIKE '%' || :searching || '%' ")
    fun search(searching: String): List<Food>

    @Query("SELECT * FROM tableFood")
    fun getAll(): List<Food>

    @Query("DELETE FROM tableFood")
    fun deleteAll()
}

