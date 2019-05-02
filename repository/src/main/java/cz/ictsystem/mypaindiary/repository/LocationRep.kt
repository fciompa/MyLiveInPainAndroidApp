package cz.ictsystem.mypaindiary.repository

import cz.ictsystem.mypaindiary.database.LocationEntity

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