package com.aliza.alizaandroid.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(

    val id:Int,
    val name: String,
    val course: String,
    val score: Int

) :Parcelable