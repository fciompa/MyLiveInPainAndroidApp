package cz.ictsystem.mypaindiary.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import cz.ictsystem.mypaindiary.repository.AppRepository
import cz.ictsystem.mypaindiary.storage.AppDb
import org.junit.*

class AppPropertiesTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDb
    private lateinit var rep: AppRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDb::class.java
        ).build()

        rep = AppRepository(db)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun isNewInstallation() {
        val properties = AppProperties(rep)
        val newInstallation1 = properties.isNewInstallation()
        Assert.assertTrue(newInstallation1.getValueForTest()!!)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallation()
        Assert.assertFalse(newInstallation2.getValueForTest()!!)
    }

    @Test
    fun isNewInstallationValue() {
        val properties = AppProperties(rep)
        val newInstallation1 = properties.isNewInstallationValue()
        Assert.assertTrue(newInstallation1)
        properties.setNewInstallation(false)
        val newInstallation2 = properties.isNewInstallationValue()
        Assert.assertFalse(newInstallation2)
    }

}