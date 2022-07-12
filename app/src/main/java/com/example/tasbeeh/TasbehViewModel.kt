package com.example.tasbeeh

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.tasbeeh.data.local.ZikrEntity
import com.example.tasbeeh.data.local.ZikrLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TasbehViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allZikr: LiveData<List<ZikrEntity>>
    private val repository: TasbehRepository

    init {

        val dao = ZikrLocal.getLocalDB(application).zikrDao()
        repository = TasbehRepository(dao)
        allZikr = repository.allZikrs
    }

    fun deleteZikr(zikrEntity: ZikrEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(zikrEntity)
    }

    fun addZikr(zikrEntity: ZikrEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(zikrEntity)
    }
}