package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.*

class DescriptionDaoTest {

    private val DESCRIPTIONS = listOf(
        DescriptionEntity(0, "Description 02"),
        DescriptionEntity(0, "Description 01")
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
        Assert.assertEquals(0, dao.descriptions().getValueForTest()?.size)
    }

    @Test
    fun insert1() = runBlocking {
        Assert.assertEquals(0, dao.descriptions().getValueForTest()?.size)
        dao.insertDescription(DescriptionEntity(0, "Description"))
        Assert.assertEquals(1, dao.descriptions().getValueForTest()?.size)
    }

    @Test
    fun insert2() = runBlocking {
        dao.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals(2, dao.descriptions().getValueForTest()?.size)
    }

    @Test
    fun insert3() = runBlocking {
        var size = 0
        var observer = Observer<List<DescriptionEntity>> {
            size = it.size
        }
        var descriptions = dao.descriptions()
        descriptions.observeForever(observer)

        Assert.assertEquals(0, size)
        dao.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals(2, size)
    }

    @Test
    fun load() = runBlocking {
        dao.insertDescriptions(DESCRIPTIONS)
        Assert.assertEquals("Description 01", dao.descriptions().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, dao.descriptions().getValueForTest()?.get(0)?.id)
    }

}