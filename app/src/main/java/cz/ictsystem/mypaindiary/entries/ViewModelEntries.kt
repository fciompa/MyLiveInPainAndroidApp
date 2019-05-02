package cz.ictsystem.mypaindiary.entries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.ictsystem.mypaindiary.domain.Domain
import cz.ictsystem.mypaindiary.domain.entity.Entry

class ViewModelEntries(domain: Domain) : ViewModel() {
    val entries: LiveData<List<Entry>> = domain.loadEntries()
}