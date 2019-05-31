package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.repository.RepositoryImpl
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DomainImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var domain: Domain

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        val db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        val rep = RepositoryImpl(db.dao)
        domain = DomainImpl(rep)
    }

    @Test
    fun initDomain() {
        val entries = domain.loadEntries().getValueForTest()
        Assert.assertEquals(9, entries?.size)

        val descriptions = domain.loadDescription().getValueForTest()
        Assert.assertEquals(2, descriptions?.size)

        val locations = domain.loadLocation().getValueForTest()
        Assert.assertEquals(2, locations?.size)
    }

    @Test
    fun loadEntries() {
    }

    @Test
    fun loadEntries1() {
    }

    @Test
    fun insertEntry() {
    }

    @Test
    fun insertEntries() {
    }

    @Test
    fun deleteEntry() {
    }

    @Test
    fun deleteEntries() {
    }

    @Test
    fun loadDescription() {
    }

    @Test
    fun insertDescription() {
    }

    @Test
    fun insertDescriptions() {
    }

    @Test
    fun deleteDescription() {
    }

    @Test
    fun deleteDescriptions() {
    }

    @Test
    fun loadLocation() {
    }

    @Test
    fun insertLocation() {
    }

    @Test
    fun insertLocations() {
    }

    @Test
    fun deleteLocation() {
    }

    @Test
    fun deleteLocations() {
    }

    @Test
    fun properties() {
    }
}