package cz.ictsystem.mypaindiary.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ictsystem.mypaindiary.database.*
import java.util.*
import kotlin.collections.ArrayList

class MyRepository(db: MyDb) : Repository {

    private val descriptionDao: DescriptionDao = db.descriptionDao
    private val entryDao: EntryDao = db.entryDao
    private val locationDao: LocationDao = db.locationDao
    private val propertyDao: PropertyDao = db.propertyDao

    override fun loadEntries(): LiveData<List<Entry>> {
        return Transformations.map(
            entryDao.load()
        ) {
            val entries = ArrayList<Entry>()
            it.forEach {
                entries.add(Entry(it))
            }

            entries
        }
    }

    override fun loadEntries(from: Date, to: Date): LiveData<List<Entry>> {
        return Transformations.map(
            entryDao.load(from, to)
        ) {
            val entries = ArrayList<Entry>()
            it.forEach {
                entries.add(Entry(it))
            }
            entries
        }
    }

    override fun insertEntry(entry: Entry) {
        entryDao.insert(entry.getEntity())
    }

    override fun insertEntries(entries: List<Entry>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        entryDao.insert(entities)
    }

    override fun deleteEntry(entry: Entry) {
        entryDao.delete(entry.getEntity())
    }

    override fun deleteEntries(entries: List<Entry>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(it.getEntity())
        }

        entryDao.delete(entities)
    }

    override fun loadDescription(): LiveData<List<Description>> {
        return Transformations.map(
            descriptionDao.load()
        ) {
            val descriptions = ArrayList<Description>()
            it.forEach {
                descriptions.add(Description(it))
            }

            descriptions
        }
    }

    override fun insertDescription(description: Description) {
        descriptionDao.insert(description.getEntity())
    }

    override fun insertDescriptions(descriptions: List<Description>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        descriptionDao.insert(entities)
    }

    override fun deleteDescription(description: Description) {
        descriptionDao.delete(description.getEntity())
    }

    override fun deleteDescriptions(descriptions: List<Description>) {
        val entities = ArrayList<DescriptionEntity>()
        descriptions.forEach {
            entities.add(it.getEntity())
        }

        descriptionDao.delete(entities)
    }

    override fun loadLocation(): LiveData<List<Location>> {
        return Transformations.map(
            locationDao.load()
        ) {
            val locations = ArrayList<Location>()
            it.forEach {
                locations.add(Location(it))
            }
            locations
        }
    }

    override fun insertLocation(location: Location) {
        locationDao.insert(location.getEntity())
    }

    override fun insertLocations(locations: List<Location>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        locationDao.insert(entries)
    }

    override fun deleteLocation(location: Location) {
        locationDao.delete(location.getEntity())
    }

    override fun deleteLocations(locations: List<Location>) {
        val entries = ArrayList<LocationEntity>()
        locations.forEach {
            entries.add(it.getEntity())
        }

        locationDao.delete(entries)
    }

    override fun loadProperties(): LiveData<List<Property>> {
        return Transformations.map(propertyDao.load()) {
            val properties = ArrayList<Property>()
            it.forEach {
                properties.add(Property(it))
            }
            properties
        }
    }

    override fun loadProperties(name: String): LiveData<Property> {
        return Transformations.map(propertyDao.load(name)) {
            if (it != null) Property(it) else null
        }
    }

    override fun insertProperty(property: Property) {
        propertyDao.insert(property.getEntity())
    }

    override fun insertProperties(properties: List<Property>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        propertyDao.insert(entries)
    }

    override fun deleteProperty(property: Property) {
        propertyDao.delete(property.getEntity())
    }

    override fun deleteProperties(properties: List<Property>) {
        val entries = ArrayList<PropertyEntity>()
        properties.forEach {
            entries.add(it.getEntity())
        }

        propertyDao.delete(entries)
    }
}