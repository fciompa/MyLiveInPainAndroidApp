package cz.ictsystem.mypaindiary.domain.entity

import cz.ictsystem.mypaindiary.repository.PropertyRep

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