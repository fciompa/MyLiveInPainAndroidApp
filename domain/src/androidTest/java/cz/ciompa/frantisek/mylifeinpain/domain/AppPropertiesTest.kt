package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.repository.AppRepository
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import kotlinx.coroutines.runBlocking
import org.junit.*

class AppPropertiesTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var rep: AppRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        val db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        rep = AppRepository(db.dao)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isNewInstallation() = runBlocking {
        val properties = AppProperties(rep)
        val newInstallation1 = properties.isNewInstallation()
        Assert.assertTrue(newInstallation1.getValueForTest()!!)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallation()
        Assert.assertFalse(newInstallation2.getValueForTest()!!)
    }

    @Test
    fun isNewInstallationValue() = runBlocking {
        val properties = AppProperties(rep)
        val newInstallation1 = properties.isNewInstallationValue()
        Assert.assertTrue(newInstallation1)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallationValue()
        Assert.assertFalse(newInstallation2)
    }

}