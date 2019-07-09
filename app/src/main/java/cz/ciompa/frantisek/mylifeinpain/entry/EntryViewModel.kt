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

    var id: Int
    var entryDate: Date
    var intensity: Int
    var description: String
    var note: String

    init {
        id = entry.id
        entryDate = entry.entryDate
        intensity = entry.intensity
        description = entry.description
        note = entry.note
    }

    val idToString = id.toString()
    val entryDateToString = entryDate.toString()
    val intensityToString = intensity.toString()

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            domain.insertEntry(Entry(id, entryDate, intensity, description, note))
        }
    }

}