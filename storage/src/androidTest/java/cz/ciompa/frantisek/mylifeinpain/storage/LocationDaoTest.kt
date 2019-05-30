package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.*

class LocationDaoTest {

    private val LOCATIONS = listOf(
        LocationEntity(0, "Location 02"),
        LocationEntity(0, "Location 01")
    )

    private lateinit var db: AppDb
    private lateinit var dao: AppDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, AppDb::class.java
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
    fun insert1() = runBlocking {
        Assert.assertEquals(0, dao.loadLocations().getValueForTest()?.size)
        dao.insertLocation(LocationEntity(0, "Location"))
        Assert.assertEquals(1, dao.loadLocations().getValueForTest()?.size)

    }

    @Test
    fun insert2() = runBlocking {
        dao.insertLocations(LOCATIONS)
        Assert.assertEquals(2, dao.loadLocations().getValueForTest()?.size)

    }

    @Test
    fun load() = runBlocking {
        dao.insertLocations(LOCATIONS)
        Assert.assertEquals("Location 01", dao.loadLocations().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.loadLocations().getValueForTest()?.get(0)?.id)
    }

}