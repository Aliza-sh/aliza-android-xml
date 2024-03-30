package com.aliza.alizaandroid.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableFood")
data class Food(

    @PrimaryKey(autoGenerate = true)
    val id :Int? = null,

    val txtSubject: String,
    val txtDistance: String,
    val txtPrice: String,
    val txtLocation: String,

    @ColumnInfo(name = "url")
    val urlImage: String,
    val numOfRating: Int,
    val ratingBar: Float

)
