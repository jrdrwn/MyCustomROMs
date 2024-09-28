package com.dicoding.mycustomroms

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rom(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
