package cz.ictsystem.mypaindiary.storage

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DaoProperty {
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