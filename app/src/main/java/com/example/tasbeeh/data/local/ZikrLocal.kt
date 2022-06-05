package com.example.tasbeeh.data.local

import android.content.Context
import androidx.room.Room

class ZikrLocal {
    companion object {
        fun getLocalDB(applicationContext: Context): ZikrDatabase {
            val zikrDb = Room.databaseBuilder(
                applicationContext,
                ZikrDatabase::class.java, "zikr-db"
            ).allowMainThreadQueries().build()

            return zikrDb
        }
    }
}