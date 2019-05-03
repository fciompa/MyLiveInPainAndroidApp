package cz.ictsystem.mypaindiary.repository

import androidx.lifecycle.LiveData
import java.util.*

interface Repository {
    fun loadEntries(): LiveData<List<EntryRep>>
    fun loadEntries(from: Date, to: Date): LiveData<List<EntryRep>>
    fun insertEntry(entry: EntryRep)
    fun insertEntries(entries: List<EntryRep>)
    fun deleteEntry(entry: EntryRep)
    fun deleteEntries(entries: List<EntryRep>)

    fun loadDescription(): LiveData<List<DescriptionRep>>
    fun insertDescription(description: DescriptionRep)
    fun insertDescriptions(descriptions: List<DescriptionRep>)
    fun deleteDescription(description: DescriptionRep)
    fun deleteDescriptions(descriptions: List<DescriptionRep>)

    fun loadLocation(): LiveData<List<LocationRep>>
    fun insertLocation(location: LocationRep)
    fun insertLocations(locations: List<LocationRep>)
    fun deleteLocation(location: LocationRep)
    fun deleteLocations(locations: List<LocationRep>)

    fun loadProperties(): LiveData<List<PropertyRep>>
    fun loadProperty(name: String): LiveData<PropertyRep>
    fun insertProperty(property: PropertyRep)
    fun insertProperties(properties: List<PropertyRep>)
    fun deleteProperty(property: PropertyRep)
    fun deleteProperties(properties: List<PropertyRep>)

}