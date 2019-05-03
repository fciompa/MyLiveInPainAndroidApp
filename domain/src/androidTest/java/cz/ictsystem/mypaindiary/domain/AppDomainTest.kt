package cz.ictsystem.mypaindiary.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ictsystem.mypaindiary.database.MyDb
import cz.ictsystem.mypaindiary.repository.AppRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppDomainTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: MyDb
    private lateinit var rep: AppRepository
    private lateinit var domain: Domain

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context,
            MyDb::class.java
        ).build()

        rep = AppRepository(db)
        domain = AppDomain(rep)
    }

    @Test
    fun initDomain() {

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