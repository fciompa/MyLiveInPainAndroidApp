package cz.ictsystem.mypaindiary.storage

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DescriptionDao {
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
}