package com.example.tasbeeh.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZikrDao {
    @Query("SELECT * FROM zikrentity")
    fun getAllZikrs(): LiveData<List<ZikrEntity>>

    @Query("SELECT COUNTER id FROM zikrentity")
    fun getZikrCounts(): List<Int>

    @Insert
    fun insert(zikr: ZikrEntity)

    @Insert
    fun insertAll(zikr: List<ZikrEntity>)

    @Delete
    fun delete(zikr: ZikrEntity)
}