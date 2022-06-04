package com.example.tasbeeh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    val imageInt: Int,
    val divineWord: String,
    val divineWordMeaning: String
): Parcelable