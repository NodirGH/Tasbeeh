package com.example.tasbeeh.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ZikrInfo(
    val id: Int = 0,
    val zikr: String = "",
    val translation: String = ""
) : Parcelable