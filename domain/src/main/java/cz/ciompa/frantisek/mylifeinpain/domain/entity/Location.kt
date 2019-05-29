package cz.ciompa.frantisek.mylifeinpain.domain.entity

import cz.ciompa.frantisek.mylifeinpain.repository.LocationRep

data class Location(
    val id: Int = 0,
    val value: String = ""
) {
    constructor(l: LocationRep) : this(
        l.id,
        l.value
    )

    fun getRep() = LocationRep(id, value)

}