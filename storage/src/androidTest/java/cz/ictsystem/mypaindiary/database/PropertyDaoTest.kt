package cz.ictsystem.mypaindiary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PropertyDaoTest {

    private val PROPERTIES = listOf(
        PropertyEntity(0, "Property 02", "Value 02"),
        PropertyEntity(0, "Property 01", "Value 01")
    )

    private lateinit var db: MyDb
    private lateinit var dao: PropertyDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, MyDb::class.java
        ).build()

        dao = db.propertyDao
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
        dao.insert(PropertyEntity(0, "Property", "Value"))
        assertEquals(1, dao.load().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insert(PROPERTIES)
        assertEquals(2, dao.load().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insert(PROPERTIES)
        assert(dao.load().getValueForTest()?.get(0)?.name.equals("Property 01"))
        assert(dao.load().getValueForTest()?.get(0)?.value.equals("Value 01"))
        assert(dao.load().getValueForTest()?.get(0)?.id == 2)
    }

    @Test
    fun loadByName() {
        dao.insert(PROPERTIES)
        assert(dao.load("Value 01").getValueForTest()?.name.equals("Property 01"))
        assert(dao.load("Value 01").getValueForTest()?.value.equals("Value 01"))
        assert(dao.load("Value 01").getValueForTest()?.id == 2)
        assert(dao.load("Value 02").getValueForTest()?.name.equals("Property 02"))
        assert(dao.load("Value 02").getValueForTest()?.value.equals("Value 02"))
        assert(dao.load("Value 02").getValueForTest()?.id == 1)
    }

}