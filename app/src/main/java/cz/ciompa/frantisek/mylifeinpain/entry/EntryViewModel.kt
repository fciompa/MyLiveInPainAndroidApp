package cz.ciompa.frantisek.mylifeinpain.entry

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ciompa.frantisek.mylifeinpain.BR
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class EntryViewModel(
    private val domain: Domain,
    private val entry: Entry
) : ViewModel(), Observable {

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

    /**
     * Notifies observers that all properties of this instance have changed.
     */
    private fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    /**
     * Notifies observers that a specific property has changed. The getter for the
     * property that changes should be marked with the @Bindable annotation to
     * generate a field in the BR class to be used as the fieldId parameter.
     *
     * @param fieldId The generated BR id for the Bindable field.
     */
    fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }
    private val dateFormat: DateFormat = DateFormat.getDateInstance()
    private val timeFormat: DateFormat = DateFormat.getTimeInstance()
    private var date = Date(entry.entryDate.time)

    var id: String = entry.id.toString()
    var entryDate: String = dateFormat.format(entry.entryDate)
    var entryTime: String = timeFormat.format(entry.entryDate)
    var intensity: String = entry.intensity.toString()
    var location: String = entry.location
    @Bindable
    var description: String = entry.description
    var note: String = entry.note

    fun save() {
        viewModelScope.launch(Dispatchers.IO) {
            domain.insertEntry(Entry(id.toInt(), date, intensity.toInt(), location, description, note))
        }
    }

    fun startDatePicker() {
        description = Date().toString()
        notifyPropertyChanged(BR.description)
    }

    fun startTimePicker() {

    }


}