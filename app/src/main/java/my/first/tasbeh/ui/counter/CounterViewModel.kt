package my.first.tasbeh.ui.counter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.first.tasbeh.data.TasbehRepository

class CounterViewModel constructor(private val repository: TasbehRepository = TasbehRepository.getInstance()) :
    ViewModel() {

      fun updateCount(id: Int, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateZikrCount(id, count)
        }
    }
}