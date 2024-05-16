package com.aliza.alizaandroid.model.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "student_table")
data class Student(

    @PrimaryKey
    val name: String,
    val course: String,
    val score: Int

) :Parcelable