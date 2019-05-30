package cz.ciompa.frantisek.mylifeinpain.repository

import androidx.lifecycle.LiveData
import java.util.*

interface Repository {
    fun loadEntries(): LiveData<List<EntryRep>>
    fun loadEntries(from: Date, to: Date): LiveData<List<EntryRep>>
    suspend fun insertEntry(entry: EntryRep)
    suspend fun insertEntries(entries: List<EntryRep>)
    suspend fun deleteEntry(entry: EntryRep)
    suspend fun deleteEntries(entries: List<EntryRep>)

    fun loadDescription(): LiveData<List<DescriptionRep>>
    suspend fun insertDescription(description: DescriptionRep)
    suspend fun insertDescriptions(descriptions: List<DescriptionRep>)
    suspend fun deleteDescription(description: DescriptionRep)
    suspend fun deleteDescriptions(descriptions: List<DescriptionRep>)

    fun loadLocation(): LiveData<List<LocationRep>>
    suspend fun insertLocation(location: LocationRep)
    suspend fun insertLocations(locations: List<LocationRep>)
    suspend fun deleteLocation(location: LocationRep)
    suspend fun deleteLocations(locations: List<LocationRep>)

    fun loadProperties(): LiveData<List<PropertyRep>>
    fun loadProperty(name: String): LiveData<PropertyRep>
    suspend fun insertProperty(property: PropertyRep)
    suspend fun insertProperties(properties: List<PropertyRep>)
    suspend fun deleteProperty(property: PropertyRep)
    suspend fun deleteProperties(properties: List<PropertyRep>)

}