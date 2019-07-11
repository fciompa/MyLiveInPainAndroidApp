package cz.ciompa.frantisek.mylifeinpain.repository

import cz.ciompa.frantisek.mylifeinpain.storage.EntryEntity
import java.util.*

data class EntryRep(
    val id: Int = 0,
    val entryDate: Date = Date(),
    val intensity: Int = 0,
    val location: String = "",
    val description: String = "",
    val note: String = ""
) {
    constructor(e: EntryEntity) : this(
        e.id,
        e.entryDate,
        e.intensity,
        e.description,
        e.note
    )

    fun getEntity() = EntryEntity(id, entryDate, intensity, location, description, note)
}
