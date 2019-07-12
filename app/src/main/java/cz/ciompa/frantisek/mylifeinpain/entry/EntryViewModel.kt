package cz.ciompa.frantisek.mylifeinpain.entry

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.ciompa.frantisek.mylifeinpain.BR
import cz.ciompa.frantisek.mylifeinpain.Event
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable
import java.text.DateFormat
import java.util.*

class EntryViewModel(
    private val domain: Domain,
    private val entry: Entry
) : ViewModel(), Observable, Serializable {

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
    fun notifyChange() {
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

    var date = Date(entry.entryDate.time)
        set(value) {
            field = value
            entryDate = dateFormat.format(date)
            entryTime = timeFormat.format(date)
            notifyPropertyChanged(BR.entryTime)
            notifyPropertyChanged(BR.entryDate)
        }

    var id: String = entry.id.toString()

    @Bindable
    var entryDate: String = dateFormat.format(date)
    @Bindable
    var entryTime: String = timeFormat.format(date)
    @Bindable
    var intensity: String = entry.intensity.toString()
    @Bindable
    var location: String = entry.location
        set(value) {
            field = value
            notifyPropertyChanged(BR.location)
        }
    @Bindable
    var description: String = entry.description
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }
    @Bindable
    var note: String = entry.note

    var openDataPickerDialog = MutableLiveData<Event<Boolean>>()
    var openTimePickerDialog = MutableLiveData<Event<Boolean>>()
    var openLocationList = MutableLiveData<Event<Boolean>>()
    var openDescriptionList = MutableLiveData<Event<Boolean>>()

    fun openDatePicker() = openDataPickerDialog.postValue(Event(true))
    fun openTimePicker() = openTimePickerDialog.postValue(Event(true))
    fun openLocationList() = openLocationList.postValue(Event(true))
    fun openDescriptionList() = openDescriptionList.postValue(Event(true))

    fun save() = viewModelScope.launch(Dispatchers.IO) {
        domain.insertEntry(Entry(id.toInt(), date, intensity.toInt(), location, description, note))
    }
}