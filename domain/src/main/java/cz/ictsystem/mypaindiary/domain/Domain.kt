package cz.ictsystem.mypaindiary.domain

import androidx.lifecycle.LiveData
import cz.ictsystem.mypaindiary.domain.entity.Description
import cz.ictsystem.mypaindiary.domain.entity.Entry
import cz.ictsystem.mypaindiary.domain.entity.Location
import java.util.*

interface Domain {
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

    fun properties(): Properties
}