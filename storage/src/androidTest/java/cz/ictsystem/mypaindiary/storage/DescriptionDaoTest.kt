package cz.ictsystem.mypaindiary.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.*

class DescriptionDaoTest {

    private val DESCRIPTIONS = listOf(
        DescriptionEntity(0, "Description 02"),
        DescriptionEntity(0, "Description 01")
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
        Assert.assertEquals(0, dao.loadDescriptions().getValueForTest()?.size)
    }

    @Test
    fun insert1() {
        Assert.assertEquals(0, dao.loadDescriptions().getValueForTest()?.size)
        dao.insertDescription(DescriptionEntity(0, "Description"))
        Assert.assertEquals(1, dao.loadDescriptions().getValueForTest()?.size)

    }

    @Test
    fun insert2D() {
        dao.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals(2, dao.loadDescriptions().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals("Description 01", dao.loadDescriptions().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.loadDescriptions().getValueForTest()?.get(0)?.id)
    }

}