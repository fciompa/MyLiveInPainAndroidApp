package cz.ciompa.frantisek.mylifeinpain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ciompa.frantisek.mylifeinpain.storage.*
import java.util.*
import kotlin.collections.ArrayList

class RepositoryImpl(private val dao: AppDao) : Repository {

    private val allStorageEntries: LiveData<List<EntryRep>>
    private val allStorageDescriptions: LiveData<List<DescriptionRep>>
    private val allStorageLocations: LiveData<List<LocationRep>>
    private val allStorageProperties: LiveData<List<PropertyRep>>

    init {

        allStorageEntries = Transformations.map(
            dao.entries()
        ) {
            val entries = ArrayList<EntryRep>()
            it.forEach {
                entries.add(EntryRep(it))
            }

            entries
        }

        allStorageDescriptions = Transformations.map(
            dao.descriptions()
        ) {
            val descriptions = ArrayList<DescriptionRep>()
            it.forEach {
                descriptions.add(DescriptionRep(it))
            }

            descriptions
        }

        allStorageLocations = Transformations.map(
            dao.locations()
        ) {
            val locations = ArrayList<LocationRep>()
            it.forEach {
                locations.add(LocationRep(it))
            }
            locations
        }

        allStorageProperties = Transformations.map(dao.properties()) {
            val properties = ArrayList<PropertyRep>()
            it.forEach {
                properties.add(PropertyRep(it))
            }
            properties
        }
    }

    override fun entries(): LiveData<List<EntryRep>> = allStorageEntries

    override fun entries(from: Date, to: Date): LiveData<List<EntryRep>> {
        return Transformations.map(
            dao.entries(from, to)
        ) {
            val entries = ArrayList<EntryRep>()
            it.forEach {
                entries.add(EntryRep(it))
            }
            entries
        }

    }

    override suspend fun insertEntry(entry: EntryRep) {
        dao.insertEntry(entry.getEntity())
    }

    override suspend fun insertEntries(entries: List<EntryRep>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        dao.insertEntries(entities)
    }

    override suspend fun deleteEntry(entry: EntryRep) {
        dao.deleteEntry(entry.getEntity())
    }

    override suspend fun deleteEntries(entries: List<EntryRep>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        dao.deleteEntries(entities)
    }

    override fun descriptions(): LiveData<List<DescriptionRep>> = allStorageDescriptions

    override suspend fun insertDescription(description: DescriptionRep) {
        dao.insertDescription(description.getEntity())
    }

    override suspend fun insertDescriptions(descriptions: List<DescriptionRep>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        dao.insertDescriptions(entities)
    }

    override suspend fun deleteDescription(description: DescriptionRep) {
        dao.deleteDescription(description.getEntity())
    }

    override suspend fun deleteDescriptions(descriptions: List<DescriptionRep>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        dao.deleteDescriptions(entities)
    }

    override fun locations(): LiveData<List<LocationRep>> = allStorageLocations

    override suspend fun insertLocation(location: LocationRep) {
        dao.insertLocation(location.getEntity())
    }

    override suspend fun insertLocations(locations: List<LocationRep>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        dao.insertLocations(entries)
    }

    override suspend fun deleteLocation(location: LocationRep) {
        dao.deleteLocation(location.getEntity())
    }

    override suspend fun deleteLocations(locations: List<LocationRep>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        dao.deleteLocation(entries)
    }

    override fun properties(): LiveData<List<PropertyRep>> = allStorageProperties

    override fun property(name: String): LiveData<PropertyRep> {
        return Transformations.map(dao.property(name)) {
            if (it != null) PropertyRep(it) else null
        }
    }

    override suspend fun insertProperty(property: PropertyRep) {
        dao.insertProperty(property.getEntity())
    }

    override suspend fun insertProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        dao.insertProperties(entries)
    }

    override suspend fun deleteProperty(property: PropertyRep) {
        dao.deleteProperty(property.getEntity())
    }

    override suspend fun deleteProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        dao.deleteProperties(entries)
    }
}