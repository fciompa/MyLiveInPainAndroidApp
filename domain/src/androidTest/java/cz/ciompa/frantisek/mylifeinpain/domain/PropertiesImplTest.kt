package cz.ciompa.frantisek.mylifeinpain.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ciompa.frantisek.mylifeinpain.repository.RepositoryImpl
import cz.ciompa.frantisek.mylifeinpain.storage.AppDb
import kotlinx.coroutines.runBlocking
import org.junit.*

class PropertiesImplTest {

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

    @After
    fun tearDown() {
    }

    @Test
    fun isNewInstallation() = runBlocking {
        val properties = PropertiesImpl(rep)
        val newInstallation1 = properties.isNewInstallation()
        Assert.assertTrue(newInstallation1.getValueForTest()!!)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallation()
        Assert.assertFalse(newInstallation2.getValueForTest()!!)
    }

    @Test
    fun isNewInstallationValue() = runBlocking {
        val properties = PropertiesImpl(rep)
        val newInstallation1 = properties.isNewInstallationValue()
        Assert.assertTrue(newInstallation1)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallationValue()
        Assert.assertFalse(newInstallation2)
    }

}