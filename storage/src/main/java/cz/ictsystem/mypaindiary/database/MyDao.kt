package cz.ictsystem.mypaindiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface MyDao {
    @Query("SELECT * FROM description ORDER BY value")
    fun loadDescriptions(): LiveData<List<DescriptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDescription(entity: DescriptionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDescriptions(entities: List<DescriptionEntity>)

    @Delete
    fun deleteDescription(entity: DescriptionEntity)

    @Delete
    fun deleteDescriptions(entities: List<DescriptionEntity>)

    @Query("SELECT * FROM entry ORDER BY entry_date desc")
    fun loadEntries(): LiveData<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE entry_date between :from and :to")
    fun loadEntries(from: Date, to: Date): LiveData<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntry(entity: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entities: List<EntryEntity>)

    @Delete
    fun deleteEntry(entity: EntryEntity)

    @Delete
    fun deleteEntries(entities: List<EntryEntity>)

    @Query("SELECT * FROM location ORDER BY value")
    fun loadLocations(): LiveData<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocation(entity: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLocations(entities: List<LocationEntity>)

    @Delete
    fun deleteLocation(entity: LocationEntity)

    @Delete
    fun deleteLocation(entities: List<LocationEntity>)

    @Query("SELECT * FROM property ORDER BY name")
    fun loadProperties(): LiveData<List<PropertyEntity>>

    @Query("SELECT * FROM property WHERE name = :name")
    fun loadProperty(name: String): LiveData<PropertyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProperty(entity: PropertyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProperties(entities: List<PropertyEntity>)

    @Delete
    fun deleteProperty(entity: PropertyEntity)

    @Delete
    fun deleteProperties(entities: List<PropertyEntity>)

}