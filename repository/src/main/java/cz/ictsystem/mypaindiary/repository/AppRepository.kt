package cz.ictsystem.mypaindiary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ictsystem.mypaindiary.storage.*
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

    override fun insertEntry(entry: EntryRep) {
        dao.insertEntry(entry.getEntity())
    }

    override fun insertEntries(entries: List<EntryRep>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        dao.insertEntries(entities)
    }

    override fun deleteEntry(entry: EntryRep) {
        dao.deleteEntry(entry.getEntity())
    }

    override fun deleteEntries(entries: List<EntryRep>) {
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

    override fun insertDescription(description: DescriptionRep) {
        dao.insertDescription(description.getEntity())
    }

    override fun insertDescriptions(descriptions: List<DescriptionRep>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        dao.insertDescriptions(entities)
    }

    override fun deleteDescription(description: DescriptionRep) {
        dao.deleteDescription(description.getEntity())
    }

    override fun deleteDescriptions(descriptions: List<DescriptionRep>) {
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

    override fun insertLocation(location: LocationRep) {
        dao.insertLocation(location.getEntity())
    }

    override fun insertLocations(locations: List<LocationRep>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        dao.insertLocations(entries)
    }

    override fun deleteLocation(location: LocationRep) {
        dao.deleteLocation(location.getEntity())
    }

    override fun deleteLocations(locations: List<LocationRep>) {
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

    override fun insertProperty(property: PropertyRep) {
        dao.insertProperty(property.getEntity())
    }

    override fun insertProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        dao.insertProperties(entries)
    }

    override fun deleteProperty(property: PropertyRep) {
        dao.deleteProperty(property.getEntity())
    }

    override fun deleteProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        dao.deleteProperties(entries)
    }
}