package com.aliza.alizaandroid.net.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseStudent(

    val id:Int,
    val name: String,
    val course: String,
    val score: Int

) :Parcelable