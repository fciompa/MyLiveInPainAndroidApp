package cz.ictsystem.mypaindiary.domain.entity

import cz.ictsystem.mypaindiary.repository.LocationRep

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