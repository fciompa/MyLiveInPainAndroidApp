package cz.ictsystem.mypaindiary.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import cz.ictsystem.mypaindiary.domain.entity.Entry
import cz.ictsystem.mypaindiary.repository.Repository

class AppDomain(private val repository: Repository) : Domain {

    override fun loadEntries(): LiveData<List<Entry>> = Transformations.map(repository.loadEntries()) {
        val items = ArrayList<Entry>()
        it.forEach {
            items.add(Entry(it))
        }
        items
    }


}

