package com.faza.challenge_4.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Menu(
    val name: String,
    val price: Int,
    val image: Int,
    val desc: String,
    val address: String
) : Parcelable

