package cz.ciompa.frantisek.mylifeinpain.storage

import androidx.room.Dao

@Dao
interface AppDao : DescriptionDao, DaoEntry, DaoLocation, DaoProperty