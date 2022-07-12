package com.example.tasbeeh

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tasbeeh.data.ZikrInfo
import com.example.tasbeeh.data.local.ZikrEntity
import com.example.tasbeeh.data.local.ZikrLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class TasbehViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var allZikr: LiveData<List<ZikrEntity>>
    private val repository: TasbehRepository
    var isSuccessful = MutableLiveData<Boolean>()
    init {

        val dao = ZikrLocal.getLocalDB(application).zikrDao()
        repository = TasbehRepository(dao)
        allZikr = repository.allZikrs
    }

    fun deleteZikr(zikrEntity: ZikrEntity) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(zikrEntity)
    }

    fun addZikr(zikrInfo: ZikrInfo) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.insert(zikrInfo)
            isSuccessful.postValue(true)
        }catch (e : Exception){
            Log.d("ErrorAddDB", e.message.toString())
            isSuccessful.postValue(false)
        }

    }
}