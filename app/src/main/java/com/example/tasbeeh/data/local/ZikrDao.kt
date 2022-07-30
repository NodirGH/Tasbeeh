package com.example.tasbeeh.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ZikrDao {
    @Query("SELECT * FROM zikrentity")
    fun getAllZikrs(): LiveData<List<ZikrEntity>>

    @Query("    SELECT * FROM zikrentity")
    suspend fun getZikrs(): List<ZikrEntity>

    @Query("SELECT COUNT(id) FROM zikrentity")
    suspend fun getZikrCount(): Int

    @Insert
    fun insert(zikr: ZikrEntity)

    @Insert
    fun insertAll(zikr: List<ZikrEntity>)

    @Query("DELETE FROM zikrentity WHERE id = :id")
    fun delete(id: Int)

    @Query("update zikrentity set counter = :count  where id = :id")
    suspend fun updateCount(id: Int, count: Int): Int

    @Query("UPDATE zikrentity set counter = :count WHERE id = :id")
    suspend fun refreshZikr(id: Int, count: Int): Int
}