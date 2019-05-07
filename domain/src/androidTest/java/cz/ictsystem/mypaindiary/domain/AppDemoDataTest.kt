package cz.ictsystem.mypaindiary.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ictsystem.mypaindiary.repository.AppRepository
import cz.ictsystem.mypaindiary.storage.MyDb
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppDemoDataTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: MyDb
    private lateinit var rep: AppRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context,
            MyDb::class.java
        ).build()

        rep = AppRepository(db)
    }

    @Test
    fun insert() {
        Assert.assertEquals(0, rep.loadDescription().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.loadEntries().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.loadLocation().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.loadProperties().getValueForTest()?.size ?: 0)

        AppDemoData(rep).insert()

        val descriptions = rep.loadDescription().getValueForTest()
        Assert.assertEquals(2, descriptions?.size ?: 0)
        Assert.assertEquals("Description 01", descriptions?.get(0)?.value)
        Assert.assertEquals("Description 02", descriptions?.get(1)?.value)

        val entries = rep.loadEntries().getValueForTest()
        Assert.assertEquals(9, entries?.size ?: 0)
        Assert.assertEquals("description 09", entries?.get(0)?.description)
        Assert.assertEquals("description 08", entries?.get(1)?.description)
        Assert.assertEquals("description 07", entries?.get(2)?.description)
        Assert.assertEquals("description 06", entries?.get(3)?.description)

        val locations = rep.loadLocation().getValueForTest()
        Assert.assertEquals(2, locations?.size ?: 0)
        Assert.assertEquals("Location 01", locations?.get(0)?.value)
        Assert.assertEquals("Location 02", locations?.get(1)?.value)

        Assert.assertEquals(0, rep.loadProperties().getValueForTest()?.size ?: 0)
    }
}