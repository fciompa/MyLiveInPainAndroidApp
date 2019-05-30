package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

interface DaoEntry {
    @Query("SELECT * FROM entry ORDER BY entry_date desc")
    fun loadEntries(): LiveData<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE entry_date between :from and :to")
    fun loadEntries(from: Date, to: Date): LiveData<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entity: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntries(entities: List<EntryEntity>)

    @Delete
    suspend fun deleteEntry(entity: EntryEntity)

    @Delete
    suspend fun deleteEntries(entities: List<EntryEntity>)
}