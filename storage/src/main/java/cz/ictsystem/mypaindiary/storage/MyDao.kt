package cz.ictsystem.mypaindiary.storage

import androidx.room.Dao

@Dao
interface MyDao : DescriptionDao, DaoEntry, DaoLocation, DaoProperty