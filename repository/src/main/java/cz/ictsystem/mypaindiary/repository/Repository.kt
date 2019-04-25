package cz.ictsystem.mypaindiary.repository

import androidx.lifecycle.LiveData
import java.util.*

interface Repository {
    fun loadEntries(): LiveData<List<Entry>>
    fun loadEntries(from: Date, to: Date): LiveData<List<Entry>>
    fun insertEntry(entry: Entry)
    fun insertEntries(entries: List<Entry>)
    fun deleteEntry(entry: Entry)
    fun deleteEntries(entries: List<Entry>)

    fun loadDescription(): LiveData<List<Description>>
    fun insertDescription(description: Description)
    fun insertDescriptions(descriptions: List<Description>)
    fun deleteDescription(description: Description)
    fun deleteDescriptions(descriptions: List<Description>)

    fun loadLocation(): LiveData<List<Location>>
    fun insertLocation(location: Location)
    fun insertLocations(locations: List<Location>)
    fun deleteLocation(location: Location)
    fun deleteLocations(locations: List<Location>)

    fun loadProperties(): LiveData<List<Property>>
    fun loadProperties(name: String): LiveData<Property>
    fun insertProperty(property: Property)
    fun insertProperties(properties: List<Property>)
    fun deleteProperty(property: Property)
    fun deleteProperties(properties: List<Property>)

}