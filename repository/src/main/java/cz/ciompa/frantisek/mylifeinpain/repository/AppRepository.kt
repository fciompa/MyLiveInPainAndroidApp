package cz.ciompa.frantisek.mylifeinpain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ciompa.frantisek.mylifeinpain.storage.*
import java.util.*
import kotlin.collections.ArrayList

class AppRepository(private val dao: AppDao) : Repository {

    override fun loadEntries(): LiveData<List<EntryRep>> {
        return Transformations.map(
            dao.loadEntries()
        ) {
            val entries = ArrayList<EntryRep>()
            it.forEach {
                entries.add(EntryRep(it))
            }

            entries
        }
    }

    override fun loadEntries(from: Date, to: Date): LiveData<List<EntryRep>> {
        return Transformations.map(
            dao.loadEntries(from, to)
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

    override fun loadDescription(): LiveData<List<DescriptionRep>> {
        return Transformations.map(
            dao.loadDescriptions()
        ) {
            val descriptions = ArrayList<DescriptionRep>()
            it.forEach {
                descriptions.add(DescriptionRep(it))
            }

            descriptions
        }
    }

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

    override fun loadLocation(): LiveData<List<LocationRep>> {
        return Transformations.map(
            dao.loadLocations()
        ) {
            val locations = ArrayList<LocationRep>()
            it.forEach {
                locations.add(LocationRep(it))
            }
            locations
        }
    }

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

    override fun loadProperties(): LiveData<List<PropertyRep>> {
        return Transformations.map(dao.loadProperties()) {
            val properties = ArrayList<PropertyRep>()
            it.forEach {
                properties.add(PropertyRep(it))
            }
            properties
        }
    }

    override fun loadProperty(name: String): LiveData<PropertyRep> {
        return Transformations.map(dao.loadProperty(name)) {
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