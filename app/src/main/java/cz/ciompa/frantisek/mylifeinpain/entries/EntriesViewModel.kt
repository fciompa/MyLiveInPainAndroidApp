package cz.ciompa.frantisek.mylifeinpain.entries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class EntriesViewModel(
    private var domain: Domain
) : ViewModel() {

    val entries: LiveData<List<Entry>> = domain.entries()
    val emptyList = entries.value?.isEmpty()

    fun insert(entryDate: Date, intensity: Int, description: String, note: String) {

        viewModelScope.launch(Dispatchers.IO) {
            domain.insertEntry(Entry(0, entryDate, intensity, description, note))
        }

    }
}