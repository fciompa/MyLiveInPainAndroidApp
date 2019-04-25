package cz.ictsystem.mypaindiary.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ictsystem.mypaindiary.database.MyDb
import junit.framework.Assert.assertEquals
import org.junit.*
import java.util.*

class MyRepositoryTest {

    private val DESCRIPTIONS = listOf(
        Description(0, "Description 02"),
        Description(0, "Description 01")
    )

    private val ENTRIES = listOf(
        Entry(0, Date(2019, 1, 20, 4, 35), 3, "description 03", "note 03"),
        Entry(0, Date(2019, 1, 20, 4, 30), 2, "description 02", "note 02"),
        Entry(0, Date(2019, 1, 20, 4, 25), 1, "description 01", "note 01"),

        Entry(0, Date(2019, 1, 21, 5, 35), 3, "description 06", "note 06"),
        Entry(0, Date(2019, 1, 21, 5, 30), 2, "description 05", "note 05"),
        Entry(0, Date(2019, 1, 21, 5, 25), 1, "description 04", "note 04"),

        Entry(0, Date(2019, 1, 22, 6, 35), 3, "description 09", "note 09"),
        Entry(0, Date(2019, 1, 22, 6, 30), 2, "description 08", "note 08"),
        Entry(0, Date(2019, 1, 22, 6, 25), 1, "description 07", "note 07")
    )

    private val LOCATIONS = listOf(
        Location(0, "Location 02"),
        Location(0, "Location 01")
    )

    private val PROPERTIES = listOf(
        Property(0, "Property 02", "Value 02"),
        Property(0, "Property 01", "Value 01")
    )

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: MyDb
    private lateinit var rep: MyRepository

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context,
            MyDb::class.java
        ).build()

        rep = MyRepository(db)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun initDescription() {
        Assert.assertEquals(0, rep.loadDescription().getValueForTest()?.size)
    }

    @Test
    fun insert1Description() {
        Assert.assertEquals(0, rep.loadDescription().getValueForTest()?.size)
        rep.insertDescription(Description(0, "Description"))
        Assert.assertEquals(1, rep.loadDescription().getValueForTest()?.size)

    }

    @Test
    fun insert2Description() {
        rep.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals(2, rep.loadDescription().getValueForTest()?.size)

    }

    @Test
    fun loadDescription() {
        rep.insertDescriptions(DESCRIPTIONS)
        assert(rep.loadDescription().getValueForTest()?.get(0)?.value.equals("Description 01"))
        assert(rep.loadDescription().getValueForTest()?.get(0)?.id == 2)
    }

    @Test
    fun initEntries() {
        Assert.assertEquals(0, rep.loadEntries().getValueForTest()?.size)
    }

    @Test
    fun insert1Entries() {
        Assert.assertEquals(0, rep.loadEntries().getValueForTest()?.size)
        rep.insertEntry(Entry(0, Date(2019, 1, 20, 4, 35), 1, "description", "note"))
        Assert.assertEquals(1, rep.loadEntries().getValueForTest()?.size)

    }

    @Test
    fun insert2Entries() {
        rep.insertEntries(ENTRIES)
        assertEquals(9, rep.loadEntries().getValueForTest()?.size)
    }

    @Test
    fun loadEntries() {
        rep.insertEntries(ENTRIES)
        assert(rep.loadEntries().getValueForTest()?.get(0)?.entryDate?.time == (Date(2019, 1, 22, 6, 35).time))
        assert(rep.loadEntries().getValueForTest()?.get(0)?.intensity == 3)
        assert(rep.loadEntries().getValueForTest()?.get(0)?.description.equals("description 09"))
        assert(rep.loadEntries().getValueForTest()?.get(0)?.note.equals("note 09"))
        assert(rep.loadEntries().getValueForTest()?.get(0)?.id == 9)
    }

    @Test
    fun loadByDateInterval1Entries() {
        rep.insertEntries(ENTRIES)
        Assert.assertEquals(
            1,
            rep.loadEntries(Date(2019, 1, 21, 5, 30), Date(2019, 1, 21, 5, 30)).getValueForTest()?.size
        )
        assert(
            rep.loadEntries(
                Date(2019, 1, 21, 5, 30),
                Date(2019, 1, 21, 5, 30)
            ).getValueForTest()?.get(0)?.description.equals("description 08")
        )
    }

    @Test
    fun loadByDateInterval2Entries() {
        rep.insertEntries(ENTRIES)
        val list = rep.loadEntries(Date(2019, 1, 21), Date(2019, 1, 22)).getValueForTest()
        Assert.assertEquals(3, list?.size)
        assert(list?.get(0)?.description.equals("description 04"))
        assert(list?.get(2)?.description.equals("description 06"))
    }

    @Test
    fun initLocation() {
        assertEquals(0, rep.loadLocation().getValueForTest()?.size)
    }

    @Test
    fun insert1Location() {
        assertEquals(0, rep.loadLocation().getValueForTest()?.size)
        rep.insertLocation(Location(0, "Location"))
        assertEquals(1, rep.loadLocation().getValueForTest()?.size)

    }

    @Test
    fun insert2Location() {
        rep.insertLocations(LOCATIONS)
        assertEquals(2, rep.loadLocation().getValueForTest()?.size)

    }

    @Test
    fun loadLocation() {
        rep.insertLocations(LOCATIONS)
        assert(rep.loadLocation().getValueForTest()?.get(0)?.value.equals("Location 01"))
        assert(rep.loadLocation().getValueForTest()?.get(0)?.id == 2)
    }

    @Test
    fun initProperty() {
        assertEquals(0, rep.loadProperties().getValueForTest()?.size)
    }

    @Test
    fun insert1Property() {
        assertEquals(0, rep.loadProperties().getValueForTest()?.size)
        rep.insertProperty(Property(0, "Property", "Value"))
        assertEquals(1, rep.loadProperties().getValueForTest()?.size)

    }

    @Test
    fun insert2Property() {
        rep.insertProperties(PROPERTIES)
        assertEquals(2, rep.loadProperties().getValueForTest()?.size)

    }

    @Test
    fun loadProperty() {
        rep.insertProperties(PROPERTIES)
        assert(rep.loadProperties().getValueForTest()?.get(0)?.name.equals("Property 01"))
        assert(rep.loadProperties().getValueForTest()?.get(0)?.value.equals("Value 01"))
        assert(rep.loadProperties().getValueForTest()?.get(0)?.id == 2)
    }

    @Test
    fun loadByNameProperty() {
        rep.insertProperties(PROPERTIES)
        assert(rep.loadProperties("Value 01").getValueForTest()?.name.equals("Property 01"))
        assert(rep.loadProperties("Value 01").getValueForTest()?.value.equals("Value 01"))
        assert(rep.loadProperties("Value 01").getValueForTest()?.id == 2)
        assert(rep.loadProperties("Value 02").getValueForTest()?.name.equals("Property 02"))
        assert(rep.loadProperties("Value 02").getValueForTest()?.value.equals("Value 02"))
        assert(rep.loadProperties("Value 02").getValueForTest()?.id == 1)
    }

}