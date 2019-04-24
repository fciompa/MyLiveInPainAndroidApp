package cz.ictsystem.mypaindiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface DescriptionDao {
    @Query("SELECT * FROM description ORDER BY value")
    fun getAll(): LiveData<List<DescriptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: DescriptionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<DescriptionEntity>)

    @Delete
    fun delete(entity: DescriptionEntity)

    @Delete
    fun delete(entities: List<DescriptionEntity>)
}