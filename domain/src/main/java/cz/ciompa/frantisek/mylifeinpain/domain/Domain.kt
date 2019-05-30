package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.lifecycle.LiveData
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Description
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Location
import java.util.*

interface Domain {
    fun loadEntries(): LiveData<List<Entry>>
    fun loadEntries(from: Date, to: Date): LiveData<List<Entry>>
    suspend fun insertEntry(entry: Entry)
    suspend fun insertEntries(entries: List<Entry>)
    suspend fun deleteEntry(entry: Entry)
    suspend fun deleteEntries(entries: List<Entry>)

    fun loadDescription(): LiveData<List<Description>>
    suspend fun insertDescription(description: Description)
    suspend fun insertDescriptions(descriptions: List<Description>)
    suspend fun deleteDescription(description: Description)
    suspend fun deleteDescriptions(descriptions: List<Description>)

    fun loadLocation(): LiveData<List<Location>>
    suspend fun insertLocation(location: Location)
    suspend fun insertLocations(locations: List<Location>)
    suspend fun deleteLocation(location: Location)
    suspend fun deleteLocations(locations: List<Location>)

    fun properties(): Properties
}