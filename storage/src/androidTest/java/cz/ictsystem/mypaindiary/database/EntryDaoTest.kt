package cz.ictsystem.mypaindiary.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class EntryDaoTest {

    private val ENTRIES = listOf(
        EntryEntity(0, Date(2019, 1, 20, 4, 35), 3, "description 03", "note 03"),
        EntryEntity(0, Date(2019, 1, 20, 4, 30), 2, "description 02", "note 02"),
        EntryEntity(0, Date(2019, 1, 20, 4, 25), 1, "description 01", "note 01"),

        EntryEntity(0, Date(2019, 1, 21, 5, 35), 3, "description 06", "note 06"),
        EntryEntity(0, Date(2019, 1, 21, 5, 30), 2, "description 05", "note 05"),
        EntryEntity(0, Date(2019, 1, 21, 5, 25), 1, "description 04", "note 04"),

        EntryEntity(0, Date(2019, 1, 22, 6, 35), 3, "description 09", "note 09"),
        EntryEntity(0, Date(2019, 1, 22, 6, 30), 2, "description 08", "note 08"),
        EntryEntity(0, Date(2019, 1, 22, 6, 25), 1, "description 07", "note 07")
    )

    private lateinit var db: MyDb
    private lateinit var dao: EntryDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context, MyDb::class.java
        ).build()

        dao = db.entryDao
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
        dao.insert(EntryEntity(0, Date(2019, 1, 20, 4, 35), 1, "description", "note"))
        assertEquals(1, dao.load().getValueForTest()?.size)

    }

    @Test
    fun insert2() {
        dao.insert(ENTRIES)
        assertEquals(9, dao.load().getValueForTest()?.size)

    }

    @Test
    fun load() {
        dao.insert(ENTRIES)
        assert(dao.load().getValueForTest()?.get(0)?.entryDate?.time == (Date(2019, 1, 22, 6, 35).time))
        assert(dao.load().getValueForTest()?.get(0)?.intensity == 3)
        assert(dao.load().getValueForTest()?.get(0)?.description.equals("description 09"))
        assert(dao.load().getValueForTest()?.get(0)?.note.equals("note 09"))
        assert(dao.load().getValueForTest()?.get(0)?.id == 9)
    }

    @Test
    fun loadByDateInterval1() {
        dao.insert(ENTRIES)
        assertEquals(1, dao.load(Date(2019, 1, 21, 5, 30), Date(2019, 1, 21, 5, 30)).getValueForTest()?.size)
        assert(
            dao.load(
                Date(2019, 1, 21, 5, 30),
                Date(2019, 1, 21, 5, 30)
            ).getValueForTest()?.get(0)?.description.equals("description 08")
        )
    }

    @Test
    fun loadByDateInterval2() {
        dao.insert(ENTRIES)
        val list = dao.load(Date(2019, 1, 21), Date(2019, 1, 22)).getValueForTest()
        assertEquals(3, list?.size)
        assert(list?.get(0)?.description.equals("description 04"))
        assert(list?.get(2)?.description.equals("description 06"))
    }

}