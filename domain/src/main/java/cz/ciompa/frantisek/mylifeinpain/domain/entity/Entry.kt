package cz.ciompa.frantisek.mylifeinpain.domain.entity

import cz.ciompa.frantisek.mylifeinpain.repository.EntryRep
import java.util.*

data class Entry(
    val id: Int = 0,
    val entryDate: Date = Date(),
    val intensity: Int = 0,
    val location: String = "",
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

    fun getRep() = EntryRep(id, entryDate, intensity, location, description, note)

}
