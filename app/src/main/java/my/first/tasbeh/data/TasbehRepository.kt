package my.first.tasbeh.data

import androidx.lifecycle.LiveData
import my.first.tasbeh.data.local.ZikrDao
import my.first.tasbeh.data.local.ZikrEntity
import my.first.tasbeh.data.local.ZikrLocal
import my.first.tasbeh.model.ZikrInfo

class TasbehRepository(private val zikrDao: ZikrDao = ZikrLocal.zikrDatabase.zikrDao()) {

    val allZikrs: LiveData<List<ZikrEntity>> = zikrDao.getAllZikrs()

    suspend fun insertInitialData() {
        val dbItemCount = zikrDao.getZikrCount()
        if (dbItemCount == 0) {
            val initialData = ZikrData.getZikrs().map { it.mapToEntity() }
            zikrDao.insertAll(initialData)
        }
    }

    suspend fun updateZikrCount(id: Int, count: Int) {
        zikrDao.updateCount(id, count)
    }

    fun refreshZikrCount(id: Int, count: Int) {
        zikrDao.refreshZikr(id, count)
    }

    fun insert(zikrInfo: ZikrInfo) {
        zikrDao.insert(zikrInfo.mapToEntity())
    }

    fun delete(id: Int) {
        zikrDao.delete(id)
    }

    companion object {
        fun getInstance(): TasbehRepository {
            return TasbehRepository()
        }
    }
}