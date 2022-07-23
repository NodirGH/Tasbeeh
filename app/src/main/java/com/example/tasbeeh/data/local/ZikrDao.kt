package com.example.tasbeeh.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZikrDao {
    @Query("SELECT * FROM zikrentity")
    fun getAllZikrs(): LiveData<List<ZikrEntity>>

    @Query("SELECT * FROM zikrentity")
    suspend fun getZikrs(): List<ZikrEntity>

    @Query("SELECT COUNT(id) FROM zikrentity")
    suspend fun getZikrCount(): Int

    @Insert
    fun insert(zikr: ZikrEntity)

    @Insert
    fun insertAll(zikr: List<ZikrEntity>)

    @Delete
    fun delete(zikr: ZikrEntity)

    @Query("update zikrentity set counter = :count  where id = :id")
    suspend fun updateCount(id: Int, count: Int) : Int
}