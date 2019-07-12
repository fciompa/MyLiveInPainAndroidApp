package cz.ciompa.frantisek.mylifeinpain.entries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import cz.ciompa.frantisek.mylifeinpain.domain.Domain
import cz.ciompa.frantisek.mylifeinpain.domain.entity.Entry

class EntriesViewModel(
    private val domain: Domain
) : ViewModel() {

    val entries: LiveData<List<Entry>> = domain.entries()
    val entryViewModelItemList: MutableMap<Int, ViewModel> = mutableMapOf()
    val emptyList = entries.value?.isEmpty()
}