package com.example.tasbeeh

import androidx.lifecycle.LiveData
import com.example.tasbeeh.data.local.ZikrDao
import com.example.tasbeeh.data.local.ZikrEntity

class TasbehRepository(private val zikrDao: ZikrDao) {

    val allZikrs: LiveData<List<ZikrEntity>> = zikrDao.getAllZikrs()

    suspend fun insert(zikrEntity: ZikrEntity) {
        zikrDao.insert(zikrEntity)
    }

    suspend fun delete(zikrEntity: ZikrEntity) {
        zikrDao.delete(zikrEntity)
    }
}