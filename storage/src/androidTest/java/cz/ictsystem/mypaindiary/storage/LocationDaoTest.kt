package cz.ictsystem.mypaindiary.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.*

class LocationDaoTest {

    private val LOCATIONS = listOf(
        LocationEntity(0, "Location 02"),
        LocationEntity(0, "Location 01")
    )

    private lateinit var db: MyDb
    private lateinit var dao: MyDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, MyDb::class.java
        ).build()

        dao = db.dao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun init() {
        Assert.assertEquals(0, dao.loadLocations().getValueForTest()?.size)
    }

    @Test
    fun insert1() {
        Assert.assertEquals(0, dao.loadLocations().getValueForTest()?.size)
        dao.insertLocation(LocationEntity(0, "Location"))
        Assert.assertEquals(1, dao.loadLocations().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insertLocations(LOCATIONS)
        Assert.assertEquals(2, dao.loadLocations().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insertLocations(LOCATIONS)
        Assert.assertEquals("Location 01", dao.loadLocations().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.loadLocations().getValueForTest()?.get(0)?.id)
    }

}