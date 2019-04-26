package cz.ictsystem.mypaindiary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.*

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
        Assert.assertEquals(0, dao.load().getValueForTest()?.size)
    }

    @Test
    fun insert1() {
        Assert.assertEquals(0, dao.load().getValueForTest()?.size)
        dao.insert(PropertyEntity(0, "Property", "Value"))
        Assert.assertEquals(1, dao.load().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insert(PROPERTIES)
        Assert.assertEquals(2, dao.load().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insert(PROPERTIES)
        Assert.assertEquals("Property 01", dao.load().getValueForTest()?.get(0)?.name)
        Assert.assertEquals("Value 01", dao.load().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.load().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun loadByName() {
        dao.insert(PROPERTIES)

        val property01 = dao.load("Property 01").getValueForTest()
        Assert.assertEquals("Property 01", property01?.name)
        Assert.assertEquals("Value 01", property01?.value)
        Assert.assertEquals(2, property01?.id)

        val property02 = dao.load("Property 02").getValueForTest()
        Assert.assertEquals("Property 02", property02?.name)
        Assert.assertEquals("Value 02", property02?.value)
        Assert.assertEquals(1, property02?.id)
    }

}