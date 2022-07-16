package com.example.tasbeeh.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ZikrEntity::class], version = 1)
abstract class ZikrDatabase : RoomDatabase() {
    abstract fun zikrDao(): ZikrDao

//    companion object {
//        fun getLocalDB(applicationContext: Context): ZikrDatabase {
//            val zikrDb = Room.databaseBuilder(
//                applicationContext,
//                ZikrDatabase::class.java, "zikr-db"
//            ).allowMainThreadQueries().build()
//
//            return zikrDb
//        }
//    }
}