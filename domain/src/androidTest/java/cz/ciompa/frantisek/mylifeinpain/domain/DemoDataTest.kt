package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.repository.RepositoryImpl
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DemoDataTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var rep: RepositoryImpl

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        val db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        rep = RepositoryImpl(db.dao)
    }

    @Test
    fun insert() = runBlocking {
        Assert.assertEquals(0, rep.descriptions().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.entries().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.locations().getValueForTest()?.size ?: 0)
        Assert.assertEquals(0, rep.properties().getValueForTest()?.size ?: 0)

        DemoData(rep).insert()

        val descriptions = rep.descriptions().getValueForTest()
        Assert.assertEquals(2, descriptions?.size ?: 0)
        Assert.assertEquals("Description 01", descriptions?.get(0)?.value)
        Assert.assertEquals("Description 02", descriptions?.get(1)?.value)

        val entries = rep.entries().getValueForTest()
        Assert.assertEquals(9, entries?.size ?: 0)
        Assert.assertEquals("description 09", entries?.get(0)?.description)
        Assert.assertEquals("description 08", entries?.get(1)?.description)
        Assert.assertEquals("description 07", entries?.get(2)?.description)
        Assert.assertEquals("description 06", entries?.get(3)?.description)

        val locations = rep.locations().getValueForTest()
        Assert.assertEquals(2, locations?.size ?: 0)
        Assert.assertEquals("Location 01", locations?.get(0)?.value)
        Assert.assertEquals("Location 02", locations?.get(1)?.value)

        Assert.assertEquals(0, rep.properties().getValueForTest()?.size ?: 0)
    }
}