package cz.ictsystem.mypaindiary.domain.entity

import cz.ictsystem.mypaindiary.repository.EntryRep
import java.util.*

data class Entry(
    val id: Int = 0,
    val entryDate: Date = Date(),
    val intensity: Int = 0,
    val description: String = "",
    val note: String = ""
) {
    constructor(e: EntryRep) : this(
        e.id,
        e.entryDate,
        e.intensity,
        e.description,
        e.note
    )

    fun getEntity() = EntryRep(id, entryDate, intensity, description, note)
}
