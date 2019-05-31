package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface DescriptionDao {
    @Query("SELECT * FROM description ORDER BY value")
    fun descriptions(): LiveData<List<DescriptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescription(entity: DescriptionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescriptions(entities: List<DescriptionEntity>)

    @Delete
    suspend fun deleteDescription(entity: DescriptionEntity)

    @Delete
    suspend fun deleteDescriptions(entities: List<DescriptionEntity>)
}