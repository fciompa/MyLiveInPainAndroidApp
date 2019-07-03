package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Description
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Location
import cz.ciompa.frantisek.mylifeinpain.repository.RepositoryImpl
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class DomainImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var domain: Domain

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        val db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        val rep = RepositoryImpl(db.dao)
        domain = DomainImpl(rep)
    }

    @Test
    fun initDomain() {
        val entries = domain.entries().getValueForTest()
        Assert.assertEquals(9, entries?.size)

        val descriptions = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions?.size)

        val locations = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations?.size)
    }

    @Test
    fun entriesAll() {
        val entries = domain.entries().getValueForTest()

        val entry0 = entries?.get(0)
        Assert.assertEquals(7, entry0?.id)
        Assert.assertEquals(Date(2019, 1, 22, 6, 35), entry0?.entryDate)
        Assert.assertEquals("description 09", entry0?.description)
        Assert.assertEquals("note 09", entry0?.note)

        val entry1 = entries?.get(1)
        Assert.assertEquals(8, entry1?.id)
        Assert.assertEquals(Date(2019, 1, 22, 6, 30), entry1?.entryDate)
        Assert.assertEquals("description 08", entry1?.description)
        Assert.assertEquals("note 08", entry1?.note)

        val entry2 = entries?.get(2)
        Assert.assertEquals(9, entry2?.id)
        Assert.assertEquals(Date(2019, 1, 22, 6, 25), entry2?.entryDate)
        Assert.assertEquals("description 07", entry2?.description)
        Assert.assertEquals("note 07", entry2?.note)

        val entry3 = entries?.get(3)
        Assert.assertEquals(4, entry3?.id)
        Assert.assertEquals(Date(2019, 1, 21, 5, 35), entry3?.entryDate)
        Assert.assertEquals("description 06", entry3?.description)
        Assert.assertEquals("note 06", entry3?.note)
    }

    @Test
    fun entriesFromTo1() {
        val from = Date(2019, 1, 20, 4, 35)
        val to = Date(2019, 1, 20, 4, 35)
        val entries = domain.entries(from, to).getValueForTest()

        Assert.assertEquals(1, entries?.size)

        val entry0 = entries?.get(0)
        Assert.assertEquals(1, entry0?.id)
        Assert.assertEquals(Date(2019, 1, 20, 4, 35), entry0?.entryDate)
        Assert.assertEquals("description 03", entry0?.description)
        Assert.assertEquals("note 03", entry0?.note)

    }

    @Test
    fun entriesFromTo2() {
        val from = Date(2019, 1, 20, 4, 26)
        val to = Date(2019, 1, 20, 4, 35)
        val entries = domain.entries(from, to).getValueForTest()

        Assert.assertEquals(2, entries?.size)

        val entry0 = entries?.get(0)
        Assert.assertEquals(2, entry0?.id)
        Assert.assertEquals(Date(2019, 1, 20, 4, 30), entry0?.entryDate)
        Assert.assertEquals("description 02", entry0?.description)
        Assert.assertEquals("note 02", entry0?.note)

        val entry1 = entries?.get(1)
        Assert.assertEquals(1, entry1?.id)
        Assert.assertEquals(Date(2019, 1, 20, 4, 35), entry1?.entryDate)
        Assert.assertEquals("description 03", entry1?.description)
        Assert.assertEquals("note 03", entry1?.note)
    }

    @Test
    fun insertEntry() = runBlocking {
        val entries1 = domain.entries().getValueForTest()

        Assert.assertEquals(9, entries1?.size)

        domain.insertEntry(Entry(0, Date(2019, 1, 1), 3, "description for new entry", "note of new entry"))

        val entries2 = domain.entries().getValueForTest()
        Assert.assertEquals(10, entries2?.size)
    }

    @Test
    fun insertEntries() = runBlocking {
        val entries1 = domain.entries().getValueForTest()

        Assert.assertEquals(9, entries1?.size)

        val newEntries = listOf(
            Entry(0, Date(2019, 1, 1), 3, "description for new entry", "note of new entry"),
            Entry(0, Date(2019, 1, 1), 3, "description for new entry", "note of new entry")
        )

        domain.insertEntries(newEntries)

        val entries2 = domain.entries().getValueForTest()
        Assert.assertEquals(11, entries2?.size)
    }

    @Test
    fun deleteEntry() = runBlocking {
        val entries1 = domain.entries().getValueForTest()
        Assert.assertEquals(9, entries1?.size)

        val date = Date(2019, 1, 21, 5, 35)
        val entry = domain.entries(date, date).getValueForTest()
        Assert.assertEquals(1, entry?.size)

        val entries2 = entry?.get(0)
        if (entries2 != null) {
            domain.deleteEntry(entries2)
        }

        val entries3 = domain.entries().getValueForTest()
        Assert.assertEquals(8, entries3?.size)

        val entry2 = domain.entries(date, date).getValueForTest()
        Assert.assertEquals(0, entry2?.size)

    }

    @Test
    fun deleteEntries() = runBlocking {
        val entries1 = domain.entries().getValueForTest()
        Assert.assertEquals(9, entries1?.size)

        val from = Date(2019, 1, 21, 0, 0)
        val to = Date(2019, 1, 21, 24, 0)
        val entries = domain.entries(from, to).getValueForTest()
        Assert.assertEquals(3, entries?.size)

        if (entries != null) {
            domain.deleteEntries(entries)
        }

        val entries3 = domain.entries().getValueForTest()
        Assert.assertEquals(6, entries3?.size)

        val entry2 = domain.entries(from, to).getValueForTest()
        Assert.assertEquals(0, entry2?.size)
    }

    @Test
    fun loadDescription() = runBlocking {
        val descriptions = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions?.size)
    }

    @Test
    fun insertDescription() = runBlocking {
        val descriptions = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions?.size)

        domain.insertDescription(Description(0, "new description value"))

        val description2 = domain.descriptions().getValueForTest()

        Assert.assertEquals(3, description2?.size)
    }

    @Test
    fun insertDescriptions() = runBlocking {
        val description = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, description?.size)

        val newDescriptions = listOf(
            Description(0, "Z new description 1"),
            Description(0, "0 new description 2")
        )

        domain.insertDescriptions(newDescriptions);

        val descrptions1 = domain.descriptions().getValueForTest()
        Assert.assertEquals(4, descrptions1?.size)
        Assert.assertEquals("0 new description 2", descrptions1?.get(0)?.value)
        Assert.assertEquals("Z new description 1", descrptions1?.get(3)?.value)

    }

    @Test
    fun deleteDescription() = runBlocking {
        val descriptions = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions?.size)
        val description1 = descriptions?.get(0);
        val description2 = descriptions?.get(1);

        if (description1 != null) {
            domain.deleteDescription(description1)
        }

        val descriptions2 = domain.descriptions().getValueForTest()
        Assert.assertEquals(1, descriptions2?.size)
        val description3 = descriptions2?.get(0)
        Assert.assertEquals(description2?.value, description3?.value)
    }

    @Test
    fun deleteDescriptions() = runBlocking {
        val descriptions1 = domain.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions1?.size)

        if (descriptions1 != null) {
            domain.deleteDescriptions(descriptions1)
        }

        val descriptions2 = domain.descriptions().getValueForTest()
        Assert.assertEquals(0, descriptions2?.size)
    }

    @Test
    fun loadLocation() = runBlocking {
        val locations1 = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations1?.size)
        val location1 = locations1?.get(0);
        val location2 = locations1?.get(1);

        Assert.assertEquals("Location 01", location1?.value)
        Assert.assertEquals("Location 02", location2?.value)

    }

    @Test
    fun insertLocation() = runBlocking {
        val locations1 = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations1?.size)

        domain.insertLocation(Location(0, "Z new location"))

        val location2 = domain.locations().getValueForTest()

        Assert.assertEquals(3, location2?.size)
        val location = location2?.get(2);
        Assert.assertEquals("Z new location", location?.value)
    }

    @Test
    fun insertLocations() = runBlocking {
        val locations1 = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations1?.size)

        val newLocations = listOf(
            Location(0, "Z new location"),
            Location(0, "A new location")
        )

        domain.insertLocations(newLocations)

        val locations2 = domain.locations().getValueForTest()
        Assert.assertNotNull(locations2)
        Assert.assertEquals(4, locations2?.size)
        val location0 = locations2?.get(0)
        val location3 = locations2?.get(3)
        Assert.assertEquals("A new location", location0?.value)
        Assert.assertEquals("Z new location", location3?.value)

    }

    @Test
    fun deleteLocation() = runBlocking {
        val locations1 = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations1?.size)
        val location0 = locations1?.get(0);
        val location1 = locations1?.get(1);

        if (location0 != null) {
            domain.deleteLocation(location0)
        }

        val locations2 = domain.locations().getValueForTest()
        Assert.assertEquals(1, locations2?.size)
        val location3 = locations2?.get(0)
        Assert.assertEquals(location1?.value, location3?.value)
    }

    @Test
    fun deleteLocations() = runBlocking {
        val locations = domain.locations().getValueForTest()
        Assert.assertEquals(2, locations?.size)

        if (locations != null) {
            domain.deleteLocations(locations)
        }

        val locations2 = domain.locations().getValueForTest()
        Assert.assertEquals(0, locations2?.size)
    }

    @Test
    fun propertiesNewInstallation1() = runBlocking {
        val newInstallation1 = domain.properties().isNewInstallationValue()
        Assert.assertFalse(newInstallation1)

        domain.properties().setNewInstallation(true)
        val newInstallation2 = domain.properties().isNewInstallationValue()
        Assert.assertTrue(newInstallation2)

        domain.properties().setNewInstallation(false)
        val newInstallation3 = domain.properties().isNewInstallationValue()
        Assert.assertFalse(newInstallation3)

    }

    @Test
    fun propertiesNewInstallation2() = runBlocking {
        val newInstallation1 = domain.properties().isNewInstallation().getValueForTest()
        if (newInstallation1 != null) {
            Assert.assertFalse(newInstallation1)
        }

        domain.properties().setNewInstallation(true)
        val newInstallation2 = domain.properties().isNewInstallation().getValueForTest()
        if (newInstallation2 != null) {
            Assert.assertTrue(newInstallation2)
        }

        domain.properties().setNewInstallation(false)
        val newInstallation3 = domain.properties().isNewInstallation().getValueForTest()
        if (newInstallation3 != null) {
            Assert.assertFalse(newInstallation3)
        }

    }
}