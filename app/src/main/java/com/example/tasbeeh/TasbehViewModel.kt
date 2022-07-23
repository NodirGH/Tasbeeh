package com.example.tasbeeh

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tasbeeh.data.ZikrData
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.data.local.ZikrEntity
import com.example.tasbeeh.data.local.ZikrLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TasbehViewModel(application: Application) : AndroidViewModel(application) {

    var allZikr: LiveData<List<ZikrEntity>>
    private val repository: TasbehRepository
    var isSuccessful = MutableLiveData<Boolean>()

    init {

        val dao = ZikrLocal.getLocalDB(application).zikrDao()
        repository = TasbehRepository(dao)
        allZikr = repository.allZikrs
    }

    fun insertInitialData(context: Context) =
        viewModelScope.launch(Dispatchers.IO) {
            val zikrEntity = ZikrLocal.getLocalDB(context).zikrDao().getZikrCounts()
            if (
                zikrEntity.isNullOrEmpty()) {
                ZikrLocal.getLocalDB(context)
                    .zikrDao()
                    .insertAll(ZikrData.getZikrs().map { it.mapToEntity() })
            }
        }

    fun deleteZikr(zikrEntity: ZikrEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(zikrEntity)
    }

    fun addZikr(zikrInfo: ZikrInfo) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.insert(zikrInfo)
            isSuccessful.postValue(true)
        } catch (e: Exception) {
            Log.d("ErrorAddDB", e.message.toString())
            //Todo error handling with messageLiveData
            isSuccessful.postValue(false)
        }
    }
}