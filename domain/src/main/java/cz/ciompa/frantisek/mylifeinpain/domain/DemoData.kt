package cz.ciompa.frantisek.mylifeinpain.domain

import cz.ciompa.frantisek.mylifeinpain.repository.DescriptionRep
import cz.ciompa.frantisek.mylifeinpain.repository.EntryRep
import cz.ciompa.frantisek.mylifeinpain.repository.LocationRep
import cz.ciompa.frantisek.mylifeinpain.repository.Repository
import java.util.*

class DemoData(private val repository: Repository) {

    private val DESCRIPTIONS = listOf(
        DescriptionRep(0, "Description 02"),
        DescriptionRep(0, "Description 01")
    )

    private val LOCATIONS = listOf(
        LocationRep(0, "Location 02"),
        LocationRep(0, "Location 01")
    )

    private val ENTRIES = listOf(
        EntryRep(0, Date(119, 1, 20, 4, 35), 3, "description 03", "note 03"),
        EntryRep(0, Date(119, 1, 20, 4, 30), 2, "description 02", "note 02"),
        EntryRep(0, Date(119, 1, 20, 4, 25), 1, "description 01", "note 01"),
        EntryRep(0, Date(119, 1, 21, 5, 35), 3, "description 06", "note 06"),
        EntryRep(0, Date(119, 1, 21, 5, 30), 2, "description 05", "note 05"),
        EntryRep(0, Date(119, 1, 21, 5, 25), 1, "description 04", "note 04"),
        EntryRep(0, Date(119, 1, 22, 6, 35), 3, "description 09", "note 09"),
        EntryRep(0, Date(119, 1, 22, 6, 30), 2, "description 08", "note 08"),
        EntryRep(0, Date(119, 1, 22, 6, 25), 1, "description 07", "note 07")
    )

    suspend fun insert() {
        repository.insertDescriptions(DESCRIPTIONS)
        repository.insertLocations(LOCATIONS)
        repository.insertEntries(ENTRIES)
    }

}