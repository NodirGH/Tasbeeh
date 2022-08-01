package com.example.tasbeeh.ui.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasbeeh.data.TasbehRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CounterViewModel constructor(private val repository: TasbehRepository = TasbehRepository.getInstance()) :
    ViewModel() {

      fun updateCount(id: Int, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateZikrCount(id, count)
        }
    }
}