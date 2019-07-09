package cz.ciompa.frantisek.mylifeinpain.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EntryViewModel(
    private var domain: Domain,
    val entry: Entry
) : ViewModel() {

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            domain.insertEntry(entry)
        }
    }

}