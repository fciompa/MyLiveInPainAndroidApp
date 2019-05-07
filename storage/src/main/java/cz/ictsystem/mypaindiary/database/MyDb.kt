package cz.ictsystem.mypaindiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Volatile
private lateinit var INSTANCE: MyDb

@Database(entities = [EntryEntity::class, LocationEntity::class, DescriptionEntity::class, PropertyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDb  : RoomDatabase() {
    abstract val dao: MyDao

    companion object {
        fun getInstance(context: Context): MyDb {
            synchronized(MyDb::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyDb::class.java,
                        "my.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}