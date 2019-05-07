package cz.ictsystem.mypaindiary.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "entry")
data class EntryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "entry_date", index = true) val entryDate: Date = Date(),
    @ColumnInfo(name = "intensity") val intensity: Int = 0,
    @ColumnInfo(name = "description")val description: String = "",
    @ColumnInfo(name = "note") val note: String = ""

)