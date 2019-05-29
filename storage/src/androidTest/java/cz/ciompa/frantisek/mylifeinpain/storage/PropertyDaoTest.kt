package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.*

class PropertyDaoTest {

    private val PROPERTIES = listOf(
        PropertyEntity(0, "Property 02", "Value 02"),
        PropertyEntity(0, "Property 01", "Value 01")
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
        Assert.assertEquals(0, dao.loadProperties().getValueForTest()?.size)
    }

    @Test
    fun insert1() {
        Assert.assertEquals(0, dao.loadProperties().getValueForTest()?.size)
        dao.insertProperty(PropertyEntity(0, "Property", "Value"))
        Assert.assertEquals(1, dao.loadProperties().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insertProperties(PROPERTIES)
        Assert.assertEquals(2, dao.loadProperties().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insertProperties(PROPERTIES)
        Assert.assertEquals("Property 01", dao.loadProperties().getValueForTest()?.get(0)?.name)
        Assert.assertEquals("Value 01", dao.loadProperties().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.loadProperties().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun loadByName() {
        dao.insertProperties(PROPERTIES)

        val property01 = dao.loadProperty("Property 01").getValueForTest()
        Assert.assertEquals("Property 01", property01?.name)
        Assert.assertEquals("Value 01", property01?.value)
        Assert.assertEquals(2, property01?.id)

        val property02 = dao.loadProperty("Property 02").getValueForTest()
        Assert.assertEquals("Property 02", property02?.name)
        Assert.assertEquals("Value 02", property02?.value)
        Assert.assertEquals(1, property02?.id)
    }

}