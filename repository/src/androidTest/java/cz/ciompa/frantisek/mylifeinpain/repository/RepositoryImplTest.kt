package cz.ciompa.frantisek.mylifeinpain.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.util.*

class RepositoryImplTest {

    private val DESCRIPTIONS = listOf(
        DescriptionRep(0, "DescriptionRep 02"),
        DescriptionRep(0, "DescriptionRep 01")
    )

    private val ENTRIES = listOf(
        EntryRep(0, Date(2019, 1, 20, 4, 35), 3, "description 03", "note 03"),
        EntryRep(0, Date(2019, 1, 20, 4, 30), 2, "description 02", "note 02"),
        EntryRep(0, Date(2019, 1, 20, 4, 25), 1, "description 01", "note 01"),

        EntryRep(0, Date(2019, 1, 21, 5, 35), 3, "description 06", "note 06"),
        EntryRep(0, Date(2019, 1, 21, 5, 30), 2, "description 05", "note 05"),
        EntryRep(0, Date(2019, 1, 21, 5, 25), 1, "description 04", "note 04"),

        EntryRep(0, Date(2019, 1, 22, 6, 35), 3, "description 09", "note 09"),
        EntryRep(0, Date(2019, 1, 22, 6, 30), 2, "description 08", "note 08"),
        EntryRep(0, Date(2019, 1, 22, 6, 25), 1, "description 07", "note 07")
    )

    private val LOCATIONS = listOf(
        LocationRep(0, "LocationRep 02"),
        LocationRep(0, "LocationRep 01")
    )

    private val PROPERTIES = listOf(
        PropertyRep(0, "PropertyRep 02", "Value 02"),
        PropertyRep(0, "PropertyRep 01", "Value 01")
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDb
    private lateinit var rep: RepositoryImpl

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        rep = RepositoryImpl(db.dao)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun initDescription() {
        Assert.assertEquals(0, rep.descriptions().getValueForTest()?.size)
    }

    @Test
    fun insert1Description() = runBlocking {
        val list1 = rep.descriptions().getValueForTest()
        Assert.assertEquals(0, list1?.size)
        rep.insertDescription(DescriptionRep(0, "DescriptionRep"))
        val list2 = rep.descriptions().getValueForTest()
        Assert.assertEquals(1, list2?.size)
        Assert.assertEquals("DescriptionRep", list2?.get(0)?.value)

    }

    @Test
    fun insert2Description() = runBlocking {
        rep.insertDescriptions(DESCRIPTIONS)
        val list = rep.descriptions().getValueForTest()
        Assert.assertEquals(2, list?.size)
    }

    @Test
    fun loadDescription() = runBlocking {
        rep.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals("DescriptionRep 01", rep.descriptions().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, rep.descriptions().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun deleteDescription() = runBlocking {
        Assert.assertEquals(0, rep.descriptions().getValueForTest()?.size)
        rep.insertDescription(DescriptionRep(0, "DescriptionRep"))
        val descriptions = rep.descriptions().getValueForTest()
        Assert.assertEquals(1, descriptions?.size)
        val description = descriptions?.get(0)
        Assert.assertNotNull(description)
        if (description != null) rep.deleteDescription(description)
        Assert.assertEquals(0, rep.descriptions().getValueForTest()?.size)
    }

    @Test
    fun initEntries() {
        Assert.assertEquals(0, rep.entries().getValueForTest()?.size)
    }

    @Test
    fun insert1Entries() = runBlocking {
        Assert.assertEquals(0, rep.entries().getValueForTest()?.size)
        rep.insertEntry(EntryRep(0, Date(2019, 1, 20, 4, 35), 1, "description", "note"))
        Assert.assertEquals(1, rep.entries().getValueForTest()?.size)

    }

    @Test
    fun insert2Entries() = runBlocking {
        rep.insertEntries(ENTRIES)
        Assert.assertEquals(9, rep.entries().getValueForTest()?.size)
    }

    @Test
    fun loadEntries() = runBlocking {
        rep.insertEntries(ENTRIES)
        val entry0 = rep.entries().getValueForTest()?.get(0)

        Assert.assertEquals(Date(2019, 1, 22, 6, 35).time, entry0?.entryDate?.time)
        Assert.assertEquals(3, entry0?.intensity)
        Assert.assertEquals("description 09", entry0?.description)
        Assert.assertEquals("note 09", entry0?.note)
        Assert.assertEquals(7, entry0?.id)
    }

    @Test
    fun loadByDateInterval1Entries() = runBlocking {
        rep.insertEntries(ENTRIES)
        Assert.assertEquals(
            1,
            rep.entries(Date(2019, 1, 21, 5, 30), Date(2019, 1, 21, 5, 30)).getValueForTest()?.size
        )
        Assert.assertEquals(
            "description 05",
            rep.entries(
                Date(2019, 1, 21, 5, 30),
                Date(2019, 1, 21, 5, 30)
            ).getValueForTest()?.get(0)?.description
        )
    }

    @Test
    fun loadByDateInterval2Entries() = runBlocking {
        rep.insertEntries(ENTRIES)
        val list = rep.entries(Date(2019, 1, 21), Date(2019, 1, 22)).getValueForTest()
        Assert.assertEquals(3, list?.size)
        Assert.assertEquals("description 04", list?.get(0)?.description)
        Assert.assertEquals("description 06", list?.get(2)?.description)
    }

    @Test
    fun loadByDateInterval3Entries() = runBlocking {
        rep.insertEntries(ENTRIES)
        val list = rep.entries(Date(2019, 1, 1), Date(2019, 1, 1)).getValueForTest()
        Assert.assertEquals(0, list?.size)
    }

    @Test
    fun initLocation() {
        Assert.assertEquals(0, rep.locations().getValueForTest()?.size)
    }

    @Test
    fun insert1Location() = runBlocking {
        Assert.assertEquals(0, rep.locations().getValueForTest()?.size)
        rep.insertLocation(LocationRep(0, "LocationRep"))
        Assert.assertEquals(1, rep.locations().getValueForTest()?.size)

    }

    @Test
    fun insert2Location() = runBlocking {
        rep.insertLocations(LOCATIONS)
        Assert.assertEquals(2, rep.locations().getValueForTest()?.size)

    }

    @Test
    fun loadLocation() = runBlocking {
        rep.insertLocations(LOCATIONS)
        Assert.assertEquals("LocationRep 01", rep.locations().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, rep.locations().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun deleteLocation() = runBlocking {
        Assert.assertEquals(0, rep.locations().getValueForTest()?.size)
        rep.insertLocation(LocationRep(0, "LocationRep"))
        val locations = rep.locations().getValueForTest()
        Assert.assertEquals(1, locations?.size)
        val location = locations?.get(0)
        Assert.assertNotNull(location)
        if (location != null) rep.deleteLocation(location)
        Assert.assertEquals(0, rep.locations().getValueForTest()?.size)
    }

    @Test
    fun initProperty() {
        Assert.assertEquals(0, rep.properties().getValueForTest()?.size)
    }

    @Test
    fun insert1Property() = runBlocking {
        Assert.assertEquals(0, rep.properties().getValueForTest()?.size)
        rep.insertProperty(PropertyRep(0, "PropertyRep", "Value"))
        Assert.assertEquals(1, rep.properties().getValueForTest()?.size)

    }

    @Test
    fun insert2Property() = runBlocking {
        rep.insertProperties(PROPERTIES)
        Assert.assertEquals(2, rep.properties().getValueForTest()?.size)

    }

    @Test
    fun loadProperty() = runBlocking {
        rep.insertProperties(PROPERTIES)
        Assert.assertEquals("PropertyRep 01", rep.properties().getValueForTest()?.get(0)?.name)
        Assert.assertEquals("Value 01", rep.properties().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, rep.properties().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun loadByNameProperty1() = runBlocking {
        rep.insertProperties(PROPERTIES)
        val property01 = rep.property("PropertyRep 01").getValueForTest()
        Assert.assertEquals("PropertyRep 01", property01?.name)
        Assert.assertEquals("Value 01", property01?.value)
        Assert.assertEquals(2, property01?.id)
        val property02 = rep.property("PropertyRep 02").getValueForTest()
        Assert.assertEquals("PropertyRep 02", property02?.name)
        Assert.assertEquals("Value 02", property02?.value)
        Assert.assertEquals(1, property02?.id)
    }

    @Test
    fun loadByNameProperty2() = runBlocking {
        rep.insertProperties(PROPERTIES)
        Assert.assertNull(rep.property("PropertyRep").getValueForTest())
    }

    @Test
    fun deleteProperty() = runBlocking {
        Assert.assertEquals(0, rep.properties().getValueForTest()?.size)
        rep.insertProperty(PropertyRep(0, "Name", "Value"))
        val properties = rep.properties().getValueForTest()
        Assert.assertEquals(1, properties?.size)
        val property = properties?.get(0)
        Assert.assertNotNull(property)
        if (property != null) rep.deleteProperty(property)
        Assert.assertEquals(0, rep.properties().getValueForTest()?.size)
    }

}