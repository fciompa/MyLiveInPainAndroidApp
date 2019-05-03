package cz.ictsystem.mypaindiary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ictsystem.mypaindiary.database.*
import java.util.*
import kotlin.collections.ArrayList

class AppRepository(db: MyDb) : Repository {

    private val descriptionDao: DescriptionDao = db.descriptionDao
    private val entryDao: EntryDao = db.entryDao
    private val locationDao: LocationDao = db.locationDao
    private val propertyDao: PropertyDao = db.propertyDao

    override fun loadEntries(): LiveData<List<EntryRep>> {
        return Transformations.map(
            entryDao.load()
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
            entryDao.load(from, to)
        ) {
            val entries = ArrayList<EntryRep>()
            it.forEach {
                entries.add(EntryRep(it))
            }
            entries
        }
    }

    override fun insertEntry(entry: EntryRep) {
        entryDao.insert(entry.getEntity())
    }

    override fun insertEntries(entries: List<EntryRep>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        entryDao.insert(entities)
    }

    override fun deleteEntry(entry: EntryRep) {
        entryDao.delete(entry.getEntity())
    }

    override fun deleteEntries(entries: List<EntryRep>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        entryDao.delete(entities)
    }

    override fun loadDescription(): LiveData<List<DescriptionRep>> {
        return Transformations.map(
            descriptionDao.load()
        ) {
            val descriptions = ArrayList<DescriptionRep>()
            it.forEach {
                descriptions.add(DescriptionRep(it))
            }

            descriptions
        }
    }

    override fun insertDescription(description: DescriptionRep) {
        descriptionDao.insert(description.getEntity())
    }

    override fun insertDescriptions(descriptions: List<DescriptionRep>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        descriptionDao.insert(entities)
    }

    override fun deleteDescription(description: DescriptionRep) {
        descriptionDao.delete(description.getEntity())
    }

    override fun deleteDescriptions(descriptions: List<DescriptionRep>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        descriptionDao.delete(entities)
    }

    override fun loadLocation(): LiveData<List<LocationRep>> {
        return Transformations.map(
            locationDao.load()
        ) {
            val locations = ArrayList<LocationRep>()
            it.forEach {
                locations.add(LocationRep(it))
            }
            locations
        }
    }

    override fun insertLocation(location: LocationRep) {
        locationDao.insert(location.getEntity())
    }

    override fun insertLocations(locations: List<LocationRep>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        locationDao.insert(entries)
    }

    override fun deleteLocation(location: LocationRep) {
        locationDao.delete(location.getEntity())
    }

    override fun deleteLocations(locations: List<LocationRep>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        locationDao.delete(entries)
    }

    override fun loadProperties(): LiveData<List<PropertyRep>> {
        return Transformations.map(propertyDao.load()) {
            val properties = ArrayList<PropertyRep>()
            it.forEach {
                properties.add(PropertyRep(it))
            }
            properties
        }
    }

    override fun loadProperty(name: String): LiveData<PropertyRep> {
        return Transformations.map(propertyDao.load(name)) {
            if (it != null) PropertyRep(it) else null
        }
    }

    override fun insertProperty(property: PropertyRep) {
        propertyDao.insert(property.getEntity())
    }

    override fun insertProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        propertyDao.insert(entries)
    }

    override fun deleteProperty(property: PropertyRep) {
        propertyDao.delete(property.getEntity())
    }

    override fun deleteProperties(properties: List<PropertyRep>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        propertyDao.delete(entries)
    }
}