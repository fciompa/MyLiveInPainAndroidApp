package cz.ciompa.frantisek.mylifeinpain.storage

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
    suspend fun insertProperty(entity: PropertyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(entities: List<PropertyEntity>)

    @Delete
    suspend fun deleteProperty(entity: PropertyEntity)

    @Delete
    suspend fun deleteProperties(entities: List<PropertyEntity>)
}