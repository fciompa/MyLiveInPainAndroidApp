package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class PropertyEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name", index = true) val name: String = "",
    @ColumnInfo(name = "value") val value: String = ""
)