package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DaoLocation {
    @Query("SELECT * FROM location ORDER BY value")
    fun loadLocations(): LiveData<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(entity: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(entities: List<LocationEntity>)

    @Delete
    suspend fun deleteLocation(entity: LocationEntity)

    @Delete
    suspend fun deleteLocation(entities: List<LocationEntity>)
}