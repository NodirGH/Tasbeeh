package com.example.tasbeeh.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ZikrEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "zikr") val zikr: String?,
    @ColumnInfo(name = "translation") val translation: String?,
    @ColumnInfo(name = "arabicWord") val arabicWord: String?,
    @ColumnInfo(name = "counter") val counter: Int?,
    @ColumnInfo(name = "isDeletable") val isDeletable : Boolean?,
    @ColumnInfo(name = "zikrAudio") val zikrAudio : String?
)