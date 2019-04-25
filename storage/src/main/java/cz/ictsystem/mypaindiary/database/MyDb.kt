package cz.ictsystem.mypaindiary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EntryEntity::class, LocationEntity::class, DescriptionEntity::class, PropertyEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyDb  : RoomDatabase() {
    abstract val entryDao: EntryDao
    abstract val descriptionDao: DescriptionDao
    abstract val locationDao: LocationDao
    abstract val propertyDao: PropertyDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: MyDb

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