package cz.ciompa.frantisek.mylifeinpain.domain.entity

import cz.ciompa.frantisek.mylifeinpain.repository.PropertyRep

data class Property(
    val id: Int = 0,
    val name: String = "",
    val value: String = ""
) {
    constructor(p: PropertyRep) : this(
        p.id,
        p.name,
        p.value
    )

    fun getEntity() = PropertyRep(id, name, value)

}