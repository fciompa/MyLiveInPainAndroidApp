package cz.ciompa.frantisek.mylifeinpain.domain

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Description
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Location
import cz.ciompa.frantisek.mylifeinpain.repository.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.collections.ArrayList

class DomainImpl(private val repository: Repository) : Domain {

    private val properties: Properties

    init {
        properties = PropertiesImpl(repository)

        runBlocking(Dispatchers.IO) {
            if (properties.isNewInstallation()) {
                DemoData(repository).insert()
                properties.setNewInstallation(false)
            }
        }
    }

    override fun entries(): LiveData<List<Entry>> {
        return Transformations.map(repository.entries()) {
            val items: List<Entry> = ArrayList()
            it.forEach {
                (items as ArrayList).add(Entry(it))
            }
            items
        }
    }

    override fun entries(from: Date, to: Date): LiveData<List<Entry>> {
        return Transformations.map(repository.entries(from, to)) {
            val items: List<Entry> = ArrayList()
            it.forEach {
                (items as ArrayList).add(Entry(it))
            }
            items
        }
    }

    override suspend fun insertEntry(entry: Entry) {
        repository.insertEntry(entry.getRep())
    }

    override suspend fun insertEntries(entries: List<Entry>) {
        val items: List<EntryRep> = ArrayList()
        entries.forEach { entry ->
            (items as ArrayList).add(entry.getRep())
        }
        repository.insertEntries(items)
    }

    override suspend fun deleteEntry(entry: Entry) {
        repository.deleteEntry(entry.getRep())
    }

    override suspend fun deleteEntries(entries: List<Entry>) {
        val items: List<EntryRep> = ArrayList()
        entries.forEach { entry ->
            (items as ArrayList).add(entry.getRep())
        }
        repository.deleteEntries(items)
    }

    override fun descriptions(): LiveData<List<Description>> {
        return Transformations.map(repository.descriptions()) {
            val items = ArrayList<Description>()
            it.forEach {
                items.add(Description(it))
            }
            items
        }
    }

    override suspend fun insertDescription(description: Description) {
        repository.insertDescription(description.getRep())
    }

    override suspend fun insertDescriptions(descriptions: List<Description>) {
        val items = ArrayList<DescriptionRep>()
        descriptions.forEach {
            items.add(it.getRep())
        }
        repository.insertDescriptions(items)
    }

    override suspend fun deleteDescription(description: Description) {
        repository.deleteDescription(description.getRep())
    }

    override suspend fun deleteDescriptions(descriptions: List<Description>) {
        val items = ArrayList<DescriptionRep>()
        descriptions.forEach {
            items.add(it.getRep())
        }
        repository.deleteDescriptions(items)
    }

    override fun locations(): LiveData<List<Location>> {
        return Transformations.map(repository.locations()) {
            val items = ArrayList<Location>()
            it.forEach {
                items.add(Location(it))
            }
            items
        }
    }

    override suspend fun insertLocation(location: Location) {
        repository.insertLocation(location.getRep())
    }

    override suspend fun insertLocations(locations: List<Location>) {
        val items = ArrayList<LocationRep>()
        locations.forEach {
            items.add(it.getRep())
        }
        repository.insertLocations(items)
    }

    override suspend fun deleteLocation(location: Location) {
        repository.deleteLocation(location.getRep())
    }

    override suspend fun deleteLocations(locations: List<Location>) {
        val items = ArrayList<LocationRep>()
        locations.forEach {
            items.add(it.getRep())
        }
        repository.deleteLocations(items)
    }

    override fun properties() = properties

    companion object {

        private lateinit var INSTANCE: Domain

        fun getInstance(context: Context): Domain {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = DomainImpl(RepositoryImpl.getInstance(context))
            }

            return INSTANCE
        }
    }
}

