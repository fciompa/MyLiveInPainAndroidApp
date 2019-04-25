package cz.ictsystem.mypaindiary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LocationDaoTest {

    private val LOCATIONS = listOf(
        LocationEntity(0, "Location 02"),
        LocationEntity(0, "Location 01")
    )

    private lateinit var db: MyDb
    private lateinit var dao: LocationDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, MyDb::class.java
        ).build()

        dao = db.locationDao
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun init() {
        assertEquals(0, dao.load().getValueForTest()?.size)
    }

    @Test
    fun insert1() {
        assertEquals(0, dao.load().getValueForTest()?.size)
        dao.insert(LocationEntity(0, "Location"))
        assertEquals(1, dao.load().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insert(LOCATIONS)
        assertEquals(2, dao.load().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insert(LOCATIONS)
        assert(dao.load().getValueForTest()?.get(0)?.value.equals("Location 01"))
        assert(dao.load().getValueForTest()?.get(0)?.id == 2)
    }

}