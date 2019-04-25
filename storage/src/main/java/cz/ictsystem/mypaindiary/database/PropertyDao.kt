package cz.ictsystem.mypaindiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface PropertyDao {
    @Query("SELECT * FROM property ORDER BY name")
    fun load(): LiveData<List<PropertyEntity>>

    @Query("SELECT * FROM property WHERE name = :name")
    fun load(name: String): LiveData<List<PropertyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: PropertyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<PropertyEntity>)

    @Delete
    fun delete(entity: PropertyEntity)

    @Delete
    fun delete(entities: List<PropertyEntity>)
}