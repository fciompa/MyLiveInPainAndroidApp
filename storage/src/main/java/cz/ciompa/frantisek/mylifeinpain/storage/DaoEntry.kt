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
    fun insertEntry(entity: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEntries(entities: List<EntryEntity>)

    @Delete
    fun deleteEntry(entity: EntryEntity)

    @Delete
    fun deleteEntries(entities: List<EntryEntity>)
}