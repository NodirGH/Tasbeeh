package com.example.tasbeeh.data

import androidx.lifecycle.LiveData
import com.example.tasbeeh.data.local.ZikrDao
import com.example.tasbeeh.data.local.ZikrEntity
import com.example.tasbeeh.data.local.ZikrLocal
import com.example.tasbeeh.model.ZikrInfo

class TasbehRepository(private val zikrDao: ZikrDao = ZikrLocal.zikrDatabase.zikrDao()) {

    val allZikrs: LiveData<List<ZikrEntity>> = zikrDao.getAllZikrs()


    suspend fun insertInitialData(){
        val dbItemCount = zikrDao.getZikrCount()
        if(dbItemCount == 0){
            val initialData = ZikrData.getZikrs().map { it.mapToEntity() }
            zikrDao.insertAll(initialData)
        }
    }

    suspend fun getZikrs() : List<ZikrEntity> {
        return zikrDao.getZikrs()
    }

    suspend fun updateZikrCount(id : Int, count : Int){
        zikrDao.updateCount(id, count)
    }
    suspend fun insert(zikrInfo: ZikrInfo) {
        zikrDao.insert(zikrInfo.mapToEntity())
    }

    suspend fun delete(id: Int) {
        zikrDao.delete(id)
    }

    companion object {
        fun getInstance() : TasbehRepository{
            return TasbehRepository()
        }
    }
}