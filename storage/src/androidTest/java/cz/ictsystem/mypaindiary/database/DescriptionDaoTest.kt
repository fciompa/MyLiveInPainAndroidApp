package cz.ictsystem.mypaindiary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DescriptionDaoTest {

    private val DESCRIPTIONS = listOf(
        DescriptionEntity(0, "Description 02"),
        DescriptionEntity(0, "Description 01")
    )

    private lateinit var db: MyDb
    private lateinit var dao: DescriptionDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, MyDb::class.java
        ).build()

        dao = db.descriptionDao
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
        dao.insert(DescriptionEntity(0, "Description"))
        assertEquals(1, dao.load().getValueForTest()?.size)

    }

    @Test
    fun insert2D() {
        dao.insert(DESCRIPTIONS)
        assertEquals(2, dao.load().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insert(DESCRIPTIONS)
        assert(dao.load().getValueForTest()?.get(0)?.value.equals("Description 01"))
        assert(dao.load().getValueForTest()?.get(0)?.id == 2)
    }

}