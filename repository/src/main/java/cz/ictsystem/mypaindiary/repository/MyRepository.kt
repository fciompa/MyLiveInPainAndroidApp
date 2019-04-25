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
            val arrayList = ArrayList<Entry>()
            it.forEach {
                arrayList.add(Entry())
            }

            arrayList
        }
    }

    override fun loadEntries(from: Date, to: Date): LiveData<List<Entry>> {
        return Transformations.map(
            entryDao.load(from, to)
        ) {
            val arrayList = ArrayList<Entry>()
            it.forEach {
                arrayList.add(Entry())
            }

            arrayList
        }
    }

    override fun insertEntry(entry: Entry) {
        entryDao.insert(EntryEntity(entry.id, entry.entryDate, entry.intensity, entry.description, entry.note))
    }

    override fun insertEntries(entries: List<Entry>) {
        val entities = ArrayList<EntryEntity>()
        entries.forEach {
            entities.add(EntryEntity(it.id, it.entryDate, it.intensity, it.description, it.note))
        }

        entryDao.insert(entities)
    }

    override fun deleteEntry(entry: Entry) {
        entryDao.delete(EntryEntity(entry.id, entry.entryDate, entry.intensity, entry.description, entry.note))
    }

    override fun deleteEntries(entries: List<Entry>) {
        val entities = ArrayList<EntryEntity>()
        entities.forEach {
            entities.add(EntryEntity(it.id, it.entryDate, it.intensity, it.description, it.note))
        }

        entryDao.delete(entities)
    }

    override fun loadDescription(): LiveData<List<Description>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertDescription(description: Description) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertDescriptions(descriptions: List<Description>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDescription(description: Description) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteDescriptions(descriptions: List<Description>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadLocation(): LiveData<List<Location>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertLocation(location: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertLocations(locations: List<Location>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteLocation(location: Location) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteLocations(locations: List<Location>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadProperties(): LiveData<List<Property>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadProperties(name: String): LiveData<Property> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertProperty(property: Property) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertProperties(properties: List<Property>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteProperty(property: Property) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteProperties(properties: List<Property>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}