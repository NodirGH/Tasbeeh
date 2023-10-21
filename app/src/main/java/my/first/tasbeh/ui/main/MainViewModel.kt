package my.first.tasbeh.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.first.tasbeh.data.TasbehRepository
import my.first.tasbeh.data.local.ZikrEntity
import my.first.tasbeh.model.ZikrInfo


class MainViewModel constructor(private val repository: TasbehRepository = TasbehRepository.getInstance()) :
    ViewModel() {

    var isSuccessful = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()
    var zikrs = MutableLiveData<List<ZikrInfo>>()
    var zikrsLocalLive: LiveData<List<ZikrEntity>>? = null

    fun insertInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertInitialData()
            } catch (e: Exception) {
                Log.d(MainViewModel::class.java.name, e.message.toString())
                errorMessage.postValue(e.message)
            }
        }
    }

    fun getZikrsLiveData() {
        zikrsLocalLive = repository.allZikrs
    }

    fun deleteZikr(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(id)
    }

    fun addZikr(zikrInfo: ZikrInfo) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repository.insert(zikrInfo)
            isSuccessful.postValue(true)
        } catch (e: Exception) {
            Log.d("ErrorAddDB", e.message.toString())
            isSuccessful.postValue(false)
        }
    }

    fun refreshZikrsCount(id: Int, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.refreshZikrCount(id, count)
        }
    }
}