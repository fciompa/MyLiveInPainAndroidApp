package cz.ictsystem.mypaindiary.domain

import androidx.lifecycle.LiveData
import cz.ictsystem.mypaindiary.domain.entity.Entry

interface Domain {
    fun loadEntries(): LiveData<List<Entry>>
}