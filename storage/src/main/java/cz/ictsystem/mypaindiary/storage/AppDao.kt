package cz.ictsystem.mypaindiary.storage

import androidx.room.Dao

@Dao
interface AppDao : DescriptionDao, DaoEntry, DaoLocation, DaoProperty