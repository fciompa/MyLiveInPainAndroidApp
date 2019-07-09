package cz.ciompa.frantisek.mylifeinpain.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class EntryViewModel(
    private val domain: Domain,
    private val entry: Entry
) : ViewModel() {

    var id: String = entry.id.toString()
    var entryDate: String = entry.entryDate.toString()
    var intensity: String = entry.intensity.toString()
    var description: String = entry.description
    var note: String = entry.note

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            domain.insertEntry(Entry(id.toInt(), Date(entryDate), intensity.toInt(), description, note))

        }
    }

}