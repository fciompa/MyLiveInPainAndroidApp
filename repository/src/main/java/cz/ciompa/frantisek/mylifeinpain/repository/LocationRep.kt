package cz.ciompa.frantisek.mylifeinpain.repository

import cz.ciompa.frantisek.mylifeinpain.storage.LocationEntity

data class LocationRep(
    val id: Int = 0,
    val value: String = ""
) {
    constructor(l: LocationEntity) : this(
        l.id,
        l.value
    )

    fun getEntity() = LocationEntity(id, value)

}