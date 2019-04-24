package cz.ictsystem.mypaindiary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry ORDER BY entry_date desc")
    fun getAll(): LiveData<List<EntryEntity>>

    @Query("SELECT * FROM entry WHERE entry_date between :from and :to")
    fun loadAllByIds(from: Date, to: Date): LiveData<List<EntryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: EntryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entities: List<EntryEntity>)

    @Delete
    fun delete(entity: EntryEntity)

    @Delete
    fun delete(entities: List<EntryEntity>)
}