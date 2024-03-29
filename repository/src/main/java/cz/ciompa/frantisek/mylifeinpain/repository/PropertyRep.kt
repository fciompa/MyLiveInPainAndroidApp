package cz.ciompa.frantisek.mylifeinpain.repository

import cz.ciompa.frantisek.mylifeinpain.storage.PropertyEntity

data class PropertyRep(
    val id: Int = 0,
    val name: String = "",
    val value: String = ""
) {
    constructor(p: PropertyEntity) : this(
        p.id,
        p.name,
        p.value
    )

    fun getEntity() = PropertyEntity(id, name, value)

}