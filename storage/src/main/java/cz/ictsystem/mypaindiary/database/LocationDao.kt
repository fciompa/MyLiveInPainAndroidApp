package cz.ictsystem.mypaindiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface LocationDao {
    @Query("SELECT * FROM location ORDER BY value")
    fun load(): LiveData<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<LocationEntity>)

    @Delete
    fun delete(entity: LocationEntity)

    @Delete
    fun delete(entities: List<LocationEntity>)
}