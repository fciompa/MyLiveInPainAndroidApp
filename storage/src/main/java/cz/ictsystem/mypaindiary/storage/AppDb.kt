package cz.ictsystem.mypaindiary.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Volatile
private lateinit var INSTANCE: AppDb

@Database(entities = [EntryEntity::class, LocationEntity::class, DescriptionEntity::class, PropertyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDb : RoomDatabase() {
    abstract val dao: AppDao

    companion object {
        fun getInstance(context: Context): AppDb {
            synchronized(AppDb::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDb::class.java,
                        "my.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}