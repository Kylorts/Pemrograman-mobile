package com.example.f1team.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class F1Team (
    val name: String,
    val base: String,
    val teamChief: String,
    val chassis: String,
    val worldChampion : String,
    val desc: String,
    val link: String,
    val imageUrl:  String,
    val imageUrlDetail: String

): Parcelable